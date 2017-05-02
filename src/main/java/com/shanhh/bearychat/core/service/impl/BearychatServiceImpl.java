package com.shanhh.bearychat.core.service.impl;

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

    @Override
    public void sendMessage(String service, BearychatMessage bearychatMessage) {
        try {
            BearychatP2pRequest p2pRequest = new BearychatP2pRequest();
            p2pRequest.setUid("=bwOKq");

            BearychatP2p p2p = openApi.p2pCreate(service, p2pRequest);
            bearychatMessage.setVchannelId(p2p.getVchannelId());

            openApi.messageCreate(service, bearychatMessage);
        } catch (Exception e) {
            log.error("send message failed: {}, {}", service, e.getMessage());
        }
    }

    @Override
    public void refreshUserCache(String service) {
        try {
            List<BearychatUser> users = openApi.userList(service);
            userCache.cacheUsers(users);
        } catch (Exception e) {
            log.error("cache users failed: {}, {}", service, e.getMessage());
        }

    }

}
