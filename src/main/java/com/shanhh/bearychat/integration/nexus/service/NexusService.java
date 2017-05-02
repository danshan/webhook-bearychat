package com.shanhh.bearychat.integration.nexus.service;

import com.shanhh.bearychat.core.webhook.service.WebhookService;
import com.shanhh.bearychat.integration.nexus.bean.NexusPayload;

/**
 * @author dan
 * @since 2017-05-02 13:49
 */
public interface NexusService extends WebhookService {
    boolean ignoreMessage(NexusPayload nexusPayload);
}
