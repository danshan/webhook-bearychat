package com.shanhh.bearychat.core.openapi;

import com.shanhh.bearychat.core.openapi.bean.BearychatMessage;
import com.shanhh.bearychat.core.openapi.bean.User;

import java.util.List;

/**
 * @author dan
 * @since 2017-05-01 09:14
 */
public interface OpenApi {

    List<User> userList() throws Exception;

    BearychatMessage messageCreate(BearychatMessage bearychatMessage) throws Exception;

}
