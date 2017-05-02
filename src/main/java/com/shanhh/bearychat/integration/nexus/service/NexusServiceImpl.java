package com.shanhh.bearychat.integration.nexus.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.shanhh.bearychat.core.openapi.bean.BearychatColor;
import com.shanhh.bearychat.core.openapi.bean.BearychatMessage;
import com.shanhh.bearychat.core.service.BearychatService;
import com.shanhh.bearychat.core.webhook.bean.WebhookPayload;
import com.shanhh.bearychat.integration.nexus.bean.NexusPayload;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * @author dan
 * @since 2017-05-02 13:49
 */
@Slf4j
@Service
public class NexusServiceImpl implements NexusService {

    @Resource
    private BearychatService bearychatService;

    @Override
    public String getService() {
        return "nexus";
    }

    @Override
    public void handle(WebhookPayload webhookPayload) {
        Preconditions.checkArgument(webhookPayload instanceof NexusPayload);
        NexusPayload nexusPayload = (NexusPayload) webhookPayload;
        if (ignoreMessage(nexusPayload)) {
            return;
        }

        bearychatService.sendMessage(getService(), convertMessage(nexusPayload));
    }

    private BearychatMessage convertMessage(NexusPayload nexusPayload) {
        BearychatMessage message = new BearychatMessage();

        List<BearychatMessage.Attachment> attachments = Lists.newLinkedList();
        BearychatMessage.Attachment attachment = new BearychatMessage.Attachment();
        attachment.setColor(buildColor(nexusPayload));
        attachment.setTitle(builtTitle(nexusPayload));
        attachment.setText(builtText(nexusPayload));
        attachments.add(attachment);

        message.setText(String.format("Nexus Component `%s` : **%s**", nexusPayload.getAction(), nexusPayload.getRepositoryName()));
        message.setAttachments(attachments);

        return message;
    }

    private String builtTitle(NexusPayload nexusPayload) {
        return String.format("%s: %s/%s",
                nexusPayload.getComponent().getFormat(),
                nexusPayload.getComponent().getGroup(),
                nexusPayload.getComponent().getName());
    }

    private String builtText(NexusPayload nexusPayload) {
        return "Version: " + nexusPayload.getComponent().getVersion();
    }

    private String buildColor(NexusPayload nexusPayload) {
        return BearychatColor.GOOD.getColor();
    }

    @Override
    public boolean ignoreMessage(NexusPayload nexusPayload) {
        return nexusPayload.getComponent() == null;
    }
}
