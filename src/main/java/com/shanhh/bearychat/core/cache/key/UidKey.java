package com.shanhh.bearychat.core.cache.key;

import com.google.common.base.Preconditions;

import lombok.Builder;

/**
 * @author dan
 * @since 2017-04-22 07:21
 */
@Builder
public class UidKey extends CacheKey {

    private static final String VERSION = "1";
    private String teamId;
    private String userId;

    @Override
    public String buildKey() {
        return String.format("%s:%s:v:%s:tid:%s:uid:%s",
                DOMAIN, getClass().getCanonicalName(), VERSION,
                Preconditions.checkNotNull(teamId),
                Preconditions.checkNotNull(userId)
        );
    }
}
