package com.shanhh.bearychat.webhook.service;

import com.shanhh.bearychat.core.openapi.bean.BearychatMessage;

/**
 * @author dan
 * @since 2017-05-01 15:01
 */
public interface MessageAdapter {

    String send(BearychatMessage bearychatMessage);

}
