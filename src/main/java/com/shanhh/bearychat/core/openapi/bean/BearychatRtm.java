package com.shanhh.bearychat.core.openapi.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author dan
 * @since 2017-05-03 13:49
 */
@Data
@NoArgsConstructor
@ToString
public class BearychatRtm implements Serializable {
    /*
    @formatter: on
    {
      "user": {
        "avatars": {
          "large": null,
          "medium": null,
          "small": null
        },
        "created": "2017-05-01T09:37:05.000+0800",
        "email": "hubot-f3a33aeb59528ea10961892ea6ba3c7c@bearychat.com",
        "full_name": null,
        "id": "=bwOKs",
        "inactive": false,
        "mobile": null,
        "name": "SonarQube",
        "profile": {
          "bio": null,
          "position": null,
          "skype": null
        },
        "role": "normal",
        "team_id": "=bwAxP",
        "type": "hubot"
      },
      "ws_host": "wss://rtm.bearychat.com/nimbus/ws:bbfc3eba2e27951a756131891fef9295"
    }
    @formatter: off
     */

    private BearychatUser user;
    @JsonProperty("ws_host")
    private String wsHost;
}
