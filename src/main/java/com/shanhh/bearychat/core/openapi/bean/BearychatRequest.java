package com.shanhh.bearychat.core.openapi.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author dan
 * @since 2017-05-01 15:32
 */
@Data
@NoArgsConstructor
@ToString
public class BearychatRequest implements Serializable {
    private String token;
}
