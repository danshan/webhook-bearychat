package com.shanhh.bearychat.core.service;

import com.shanhh.bearychat.core.openapi.bean.BearychatMessage;

/**
 * @author dan
 * @since 2017-05-01 14:10
 */
public interface BearychatService {

    void sendMessage(String service, BearychatMessage bearychatMessage);

    /**
     * 刷新用户缓存
     * @param service
     */
    void refreshUserCache(String service);

    /**
     * RTM 功能主要是负责消息长连接的，用于实时聊天
     */
    void rtmToken(String service);
}
