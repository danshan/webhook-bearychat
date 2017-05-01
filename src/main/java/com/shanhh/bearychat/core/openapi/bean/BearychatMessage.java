package com.shanhh.bearychat.core.openapi.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dan
 * @since 2017-05-01 14:23
 */
@Data
@NoArgsConstructor
@ToString
public class BearychatMessage implements Serializable {

    private static final long serialVersionUID = -916938355000058040L;

    /* @fomartter: on
    {
      "key": "1485236262366.0193",
      "updated": "2017-01-24T13:37:42.000+0000",
      "is_channel": false,
      "uid": "=bw52O",
      "fallback": null,
      "attachments": [],
      "created": "2017-01-24T13:37:42.000+0000",
      "vchannel_id": "=bw52O",
      "refer_key": null,
      "robot_id": null,
      "created_ts": 1485236262366,
      "team_id": "=bw52O",
      "subtype": "normal",
      "text": "hello"
    }
     * @formatter: off
     */

    private String key;
    private Date updated;
    private Date created;

    private String text;
    private String uid;
    @JsonProperty("vchannel_id")
    private String vchannelId;
}
