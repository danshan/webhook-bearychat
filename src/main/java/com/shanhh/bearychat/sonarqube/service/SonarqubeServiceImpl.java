package com.shanhh.bearychat.sonarqube.service;

import com.google.common.base.Preconditions;
import com.shanhh.bearychat.core.openapi.bean.BearychatMessage;
import com.shanhh.bearychat.service.BearychatService;
import com.shanhh.bearychat.sonarqube.bean.SonarqubeMessage;
import com.shanhh.bearychat.webhook.bean.WebhookMessage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dan
 * @since 2017-05-01 15:10
 */
@Service
@Slf4j
public class SonarqubeServiceImpl implements SonarqubeService {

    @Resource
    private BearychatService bearychatService;

    @Override
    public String getService() {
        return "sonarqube";
    }

    @Override
    public void handle(WebhookMessage webhookMessage) {
        Preconditions.checkArgument(webhookMessage instanceof SonarqubeMessage);
        SonarqubeMessage sonarqubeMessage = (SonarqubeMessage) webhookMessage;

        bearychatService.sendMessage(getService(), convertMessage(sonarqubeMessage));
    }

    private BearychatMessage convertMessage(SonarqubeMessage sonarqubeMessage) {
        BearychatMessage message = new BearychatMessage();

//        message.setText(String.format("%s: `%s`", sonarqubeMessage.getProject().getName(), sonarqubeMessage.getStatus()));
        return message;
    }

    @Override
    public boolean ignoreMessage(SonarqubeMessage sonarqubeMessage) {
        return false;
    }
}
