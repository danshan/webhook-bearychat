package com.shanhh.bearychat.webhook.service;

import com.shanhh.bearychat.webhook.bean.WebhookMessage;

/**
 * @author dan
 * @since 2017-05-01 14:16
 */
public interface WebhookService {

    void handle(WebhookMessage webhookMessage);

}
