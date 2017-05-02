package com.shanhh.bearychat.core.openapi.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dan
 * @since 2017-05-01 10:56
 */
@Data
@NoArgsConstructor
@ToString
public class BearychatUser implements Serializable {

    private static final long serialVersionUID = -2749489935371435488L;

    /* @formatter: on
    {
        "inactive":false,
        "role":"normal",
        "email":"support@bearyinnovative.com",
        "name":"BearyBot",
        "type":"assistant",
        "created":"2017-01-11T12:28:31.000+0000",
        "id":"=bwMkR",
        "avatars":{
            "small":null,
            "medium":null,
            "large":null
        },
        "team_id":"=bw52O",
        "full_name":"倍洽小助手",
        "mobile":null,
        "profile":{
            "bio":null,
            "position":null,
            "skype":null
        }
    }
     * @formatter: off
     */
    private boolean inactive;
    private String id;
    private String role;
    private String email;
    private String name;
    private String type; // hubot, normal, assistant
    @JsonProperty("full_name")
    private String fullName;
    private String mobile;

}
