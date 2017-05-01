package com.shanhh.bearychat.service.impl;

import com.shanhh.bearychat.core.openapi.OpenApi;
import com.shanhh.bearychat.core.openapi.bean.BearychatMessage;
import com.shanhh.bearychat.core.openapi.bean.BearychatP2p;
import com.shanhh.bearychat.core.openapi.bean.BearychatP2pRequest;
import com.shanhh.bearychat.service.BearychatService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

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

    @Override
    public void sendMessage(String service, BearychatMessage bearychatMessage) {
        try {
            BearychatP2pRequest p2pRequest = new BearychatP2pRequest();
            p2pRequest.setUid("=bwOKq");

            BearychatP2p p2p = openApi.p2pCreate(service, p2pRequest);
            bearychatMessage.setVchannelId(p2p.getVchannelId());

            openApi.messageCreate(service, bearychatMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
