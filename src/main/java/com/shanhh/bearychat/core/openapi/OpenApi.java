package com.shanhh.bearychat.core.openapi;

import com.shanhh.bearychat.core.openapi.bean.BearychatMessage;
import com.shanhh.bearychat.core.openapi.bean.BearychatP2p;
import com.shanhh.bearychat.core.openapi.bean.BearychatP2pRequest;
import com.shanhh.bearychat.core.openapi.bean.BearychatRtm;
import com.shanhh.bearychat.core.openapi.bean.BearychatTeam;
import com.shanhh.bearychat.core.openapi.bean.BearychatUser;

import java.util.List;

/**
 * @author dan
 * @since 2017-05-01 09:14
 */
public interface OpenApi {

    List<BearychatUser> userList(String token) throws Exception;

    BearychatMessage messageCreate(String token, BearychatMessage bearychatMessage) throws Exception;

    BearychatP2p p2pCreate(String token, BearychatP2pRequest p2pRequest) throws Exception;

    BearychatTeam teamInfo(String token) throws Exception;

    BearychatRtm rtmStart(String token) throws Exception;
}
