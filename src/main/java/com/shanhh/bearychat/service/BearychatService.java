package com.shanhh.bearychat.service;

import com.shanhh.bearychat.core.openapi.bean.BearychatMessage;

/**
 * @author dan
 * @since 2017-05-01 14:10
 */
public interface BearychatService {
    void sendMessage(String service, BearychatMessage bearychatMessage);
}
