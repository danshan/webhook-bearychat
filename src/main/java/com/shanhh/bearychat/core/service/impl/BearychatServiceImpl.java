package com.shanhh.bearychat.core.service.impl;

import com.shanhh.bearychat.config.BearychatConfig;
import com.shanhh.bearychat.core.cache.service.UserCache;
import com.shanhh.bearychat.core.openapi.OpenApi;
import com.shanhh.bearychat.core.openapi.bean.BearychatMessage;
import com.shanhh.bearychat.core.openapi.bean.BearychatP2p;
import com.shanhh.bearychat.core.openapi.bean.BearychatP2pRequest;
import com.shanhh.bearychat.core.openapi.bean.BearychatUser;
import com.shanhh.bearychat.core.service.BearychatService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * @author dan
 * @since 2017-05-01 14:10
 */
@Service
@Slf4j
public class BearychatServiceImpl implements BearychatService {

    @Resource
    private OpenApi openApi;
    @Resource
    private UserCache userCache;
    @Resource
    private BearychatConfig bearychatConfig;
    @Resource
    private BearychatConfig.TokenManager tokenManager;

    @Override
    public void sendMessage(String service, BearychatMessage bearychatMessage) {
        try {
            BearychatP2pRequest p2pRequest = new BearychatP2pRequest();

            BearychatP2p p2p = openApi.p2pCreate(token(service), p2pRequest);
            bearychatMessage.setVchannelId(p2p.getVchannelId());

            openApi.messageCreate(token(service), bearychatMessage);
        } catch (Exception e) {
            log.error("send message failed: {}, {}", service, e.getMessage());
        }
    }

    @Override
    public void refreshUserCache(String service) {
        try {
            List<BearychatUser> users = openApi.userList(token(service));
            userCache.cacheUsers(users);
        } catch (Exception e) {
            log.error("cache users failed: {}, {}", service, e.getMessage());
        }
    }

    @Override
    public void rtmToken(String service) {
        try {
            openApi.rtmStart(token(service));
        } catch (Exception e) {
            log.error("start rtm failed: {}, {}", service, e.getMessage());
        }
    }

    private String token(String service) {
        return tokenManager.getToken(service);
    }

}
