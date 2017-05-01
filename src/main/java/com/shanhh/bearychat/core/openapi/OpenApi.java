package com.shanhh.bearychat.core.openapi;

import com.shanhh.bearychat.core.openapi.bean.BearychatMessage;
import com.shanhh.bearychat.core.openapi.bean.BearychatP2p;
import com.shanhh.bearychat.core.openapi.bean.BearychatP2pRequest;
import com.shanhh.bearychat.core.openapi.bean.BearychatUser;

import java.util.List;

/**
 * @author dan
 * @since 2017-05-01 09:14
 */
public interface OpenApi {

    List<BearychatUser> userList(String service) throws Exception;

    BearychatMessage messageCreate(String service, BearychatMessage bearychatMessage) throws Exception;

    BearychatP2p p2pCreate(String service, BearychatP2pRequest p2pRequest) throws Exception;
}
