package com.shanhh.bearychat.core.openapi.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author dan
 * @since 2017-05-01 21:10
 */
@Data
@NoArgsConstructor
@ToString
public class BearychatP2pRequest extends BearychatRequest {
    @JsonProperty("user_id")
    private String uid;
}
