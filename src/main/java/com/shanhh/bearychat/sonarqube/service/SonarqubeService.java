package com.shanhh.bearychat.sonarqube.service;

import com.shanhh.bearychat.sonarqube.bean.SonarqubeMessage;
import com.shanhh.bearychat.webhook.service.WebhookService;

/**
 * @author dan
 * @since 2017-05-01 14:24
 */
public interface SonarqubeService extends WebhookService {

    boolean ignoreMessage(SonarqubeMessage sonarqubeMessage);
}
