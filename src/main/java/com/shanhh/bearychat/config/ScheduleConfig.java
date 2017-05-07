package com.shanhh.bearychat.config;

import com.google.common.base.Preconditions;
import com.shanhh.bearychat.core.service.BearychatService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * @author dan
 * @since 2017-05-01 21:46
 */
@Component
@Slf4j
public class ScheduleConfig {

    @Resource
    private BearychatService bearychatService;
    @Resource
    private BearychatConfig bearychatConfig;

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void refreshUserCache() {
        log.debug("refresh user cache start");
        Preconditions.checkArgument(!CollectionUtils.isEmpty(bearychatConfig.getTokens()));
        bearychatService.refreshUserCache(bearychatConfig.getTokens().get(0).getName());
        log.debug("refresh user cache start");
    }

}
