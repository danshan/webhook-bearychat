package com.shanhh.bearychat.service.impl;

import com.shanhh.bearychat.core.openapi.OpenApi;
import com.shanhh.bearychat.core.openapi.bean.BearychatMessage;
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
    public void sendMessage(BearychatMessage bearychatMessage) {
        try {
            openApi.userList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
