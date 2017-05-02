package com.shanhh.bearychat.core.cache.key;

/**
 * @author dan
 * @since 2017-04-22 07:20
 */
public abstract class CacheKey {

    protected static final String DOMAIN = "bc-webhook";

    abstract String buildKey();
}
