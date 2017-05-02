package com.shanhh.bearychat.cache.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.shanhh.bearychat.cache.key.UNameKey;
import com.shanhh.bearychat.cache.key.UidKey;
import com.shanhh.bearychat.core.openapi.bean.BearychatUser;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

/**
 * @author dan
 * @since 2017-04-22 07:31
 */
@Component
@Slf4j
public class UserCacheImpl implements UserCache {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public BearychatUser loadByUsername(String username) {
        String cacheKey = UNameKey.builder()
                .username(username)
                .build().buildKey();
        String userJson = stringRedisTemplate.opsForValue().get(cacheKey);
        if (userJson == null) {
            return null;
        }
        try {
            return objectMapper.readValue(userJson, BearychatUser.class);
        } catch (IOException e) {
            log.error("parse user obj failed", e);
            return null;
        }
    }

    @Override
    public BearychatUser loadByUserId(String userId) {
        String cacheKey = UidKey.builder()
                .userId(userId)
                .build().buildKey();
        String userJson = stringRedisTemplate.opsForValue().get(cacheKey);
        if (userJson == null) {
            return null;
        }
        try {
            return objectMapper.readValue(userJson, BearychatUser.class);
        } catch (IOException e) {
            log.error("parse user obj failed", e);
            return null;
        }
    }

    @Override
    public void cacheUser(BearychatUser user) {
        try {
            RedisCallback<List<Object>> pipeline = buildUserPipeline(Lists.newArrayList(user));

            stringRedisTemplate.execute(pipeline);
        } catch (Exception e) {
            log.error("cache user obj failed", e);
        }
    }

    @Override
    public void cacheUsers(List<BearychatUser> users) {
        try {
            int count = 0;
            int batchSize = 10;
            while (count < users.size()) {
                RedisCallback<List<Object>> pipeline = buildUserPipeline(users.subList(count, Math.min(count + batchSize, users.size())));
                stringRedisTemplate.execute(pipeline);
                count += batchSize;
            }
        } catch (Exception e) {
            log.error("cache users obj failed", e);
        }
    }

    private RedisCallback<List<Object>> buildUserPipeline(Collection<BearychatUser> users) {
        StringRedisSerializer redisSerializer = new StringRedisSerializer();

        RedisCallback<List<Object>> pipelineCallback = conn -> {
            conn.openPipeline();
            users.forEach(user -> {
                try {
                    conn.set(redisSerializer.serialize(UidKey.builder().userId(user.getId()).build().buildKey()),
                            redisSerializer.serialize(objectMapper.writeValueAsString(user)));
                    conn.set(redisSerializer.serialize(UNameKey.builder().username(user.getName()).build().buildKey()),
                            redisSerializer.serialize(objectMapper.writeValueAsString(user)));
                } catch (JsonProcessingException e) {
                    log.error("serialize user failed", e);
                }
            });
            return conn.closePipeline();
        };
        return pipelineCallback;
    }
}
