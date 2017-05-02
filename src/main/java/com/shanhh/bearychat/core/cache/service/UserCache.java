package com.shanhh.bearychat.core.cache.service;


import com.shanhh.bearychat.core.openapi.bean.BearychatUser;

import java.util.List;

/**
 * @author dan
 * @since 2017-04-22 07:30
 */
public interface UserCache {

    BearychatUser loadByUsername(String username);

    BearychatUser loadByUserId(String userId);

    void cacheUser(BearychatUser user);

    void cacheUsers(List<BearychatUser> user);
}
