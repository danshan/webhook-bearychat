package com.shanhh.bearychat.core.openapi.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BearychatColor {

    GOOD("#58A359"),
    WARNING("#D1A247"),
    DANGER("#BF211B"),;

    @Getter
    private final String color;

}
