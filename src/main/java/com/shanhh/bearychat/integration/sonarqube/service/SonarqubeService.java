package com.shanhh.bearychat.integration.sonarqube.service;

import com.shanhh.bearychat.core.webhook.service.WebhookService;
import com.shanhh.bearychat.integration.sonarqube.bean.SonarqubePayload;

/**
 * @author dan
 * @since 2017-05-01 14:24
 */
public interface SonarqubeService extends WebhookService {
    boolean ignoreMessage(SonarqubePayload sonarqubeMessage);
}
