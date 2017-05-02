package com.shanhh.bearychat.core.webhook.service;

import com.shanhh.bearychat.core.webhook.bean.WebhookPayload;

/**
 * @author dan
 * @since 2017-05-01 14:16
 */
public interface WebhookService {

    String getService();

    void handle(WebhookPayload webhookPayload);

}
