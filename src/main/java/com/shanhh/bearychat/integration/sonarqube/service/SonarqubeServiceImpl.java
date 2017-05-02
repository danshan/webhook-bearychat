package com.shanhh.bearychat.integration.sonarqube.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.shanhh.bearychat.core.openapi.bean.BearychatColor;
import com.shanhh.bearychat.core.openapi.bean.BearychatMessage;
import com.shanhh.bearychat.core.service.BearychatService;
import com.shanhh.bearychat.core.webhook.bean.WebhookPayload;
import com.shanhh.bearychat.integration.sonarqube.bean.SonarqubePayload;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
    public void handle(WebhookPayload webhookPayload) {
        Preconditions.checkArgument(webhookPayload instanceof SonarqubePayload);
        SonarqubePayload sonarqubeMessage = (SonarqubePayload) webhookPayload;
        if (ignoreMessage(sonarqubeMessage)) {
            return;
        }

        bearychatService.sendMessage(getService(), convertMessage(sonarqubeMessage));
    }

    private BearychatMessage convertMessage(SonarqubePayload sonarqubeMessage) {
        BearychatMessage message = new BearychatMessage();

        List<BearychatMessage.Attachment> attachments = Lists.newLinkedList();
        if (sonarqubeMessage.getQualityGate() != null && !CollectionUtils.isEmpty(sonarqubeMessage.getQualityGate().getConditions())) {
            sonarqubeMessage.getQualityGate().getConditions().stream()
//                    .filter(condition -> !"OK".equals(condition.getStatus()))
                    .forEach(condition -> {
                        BearychatMessage.Attachment attachment = new BearychatMessage.Attachment();
                        attachment.setColor(buildColor(condition));
                        attachment.setTitle(buildTitle(condition));
                        attachment.setText(buildText(condition));
                        attachments.add(attachment);
                    });
        }
        while (attachments.size() > 30) {
            attachments.remove(attachments.size() - 1);
        }

        message.setText(String.format("[SonarQube Quality Gate](http://sonar.wanda-group.net) **%s**: `%s`", sonarqubeMessage.getProject().getName(), sonarqubeMessage.getStatus()));
        message.setAttachments(attachments);

        return message;
    }

    private String buildColor(SonarqubePayload.Condition condition) {
        String status = condition.getStatus();
        switch (status) {
            case "ERROR":
                return BearychatColor.DANGER.getColor();
            case "OK":
                return BearychatColor.GOOD.getColor();
            case "WARN":
            case "NO_VALUE":
                return BearychatColor.WARNING.getColor();
            default:
                return BearychatColor.WARNING.getColor();
        }
    }

    private String buildTitle(SonarqubePayload.Condition condition) {
        return String.format("Expected: %s %s %s", condition.getMetric(), condition.getOperator(), condition.getErrorThreshold());
    }

    private String buildText(SonarqubePayload.Condition condition) {
        return String.format("Current: %s %s", condition.getStatus(), StringUtils.trimToEmpty(condition.getValue()));
    }

    @Override
    public boolean ignoreMessage(SonarqubePayload sonarqubeMessage) {
        return false;
    }
}
