package com.shanhh.bearychat.core.openapi.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author dan
 * @since 2017-05-01 17:25
 */
@ToString
@Data
@NoArgsConstructor
public class BearychatP2p {

    /* @formatter: on
    {
      "id": "=b2y6W624k",
      "is_active": true,
      "is_member": true,
      "latest_ts": 1493623141116,
      "member_uids": [
        "=bwOKq",
        "=bwOKs"
      ],
      "team_id": "=bwAxP",
      "type": "p2p",
      "vchannel_id": "=b2y6W624k"
    }
     * @formatter: off
     */

    private String id;
    @JsonProperty("is_active")
    private boolean isActive;
    @JsonProperty("is_member")
    private boolean isMember;
    @JsonProperty("latest_ts")
    private long latestTs;
    @JsonProperty("member_uids")
    private List<String> memberUids;
    @JsonProperty("team_id")
    private String teamId;
    private String type;
    @JsonProperty("vchannel_id")
    private String vchannelId;

}
