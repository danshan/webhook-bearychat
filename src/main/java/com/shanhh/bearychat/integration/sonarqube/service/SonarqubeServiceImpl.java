package com.shanhh.bearychat.integration.sonarqube.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.shanhh.bearychat.core.openapi.bean.BearychatColor;
import com.shanhh.bearychat.core.openapi.bean.BearychatMessage;
import com.shanhh.bearychat.core.service.BearychatService;
import com.shanhh.bearychat.core.webhook.bean.WebhookPayload;
import com.shanhh.bearychat.integration.sonarqube.bean.SonarqubePayload;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
            BearychatMessage.Attachment attachment = new BearychatMessage.Attachment();
            attachment.setColor(BearychatColor.DANGER.getColor());
            StringBuffer text = new StringBuffer();

            sonarqubeMessage.getQualityGate().getConditions().stream()
                    .filter(condition -> !"OK".equals(condition.getStatus()))
                    .forEach(condition -> {
                        text.append(buildText(condition)).append("\n");
                    });
            attachment.setText(text.toString().trim());
            attachments.add(attachment);
        }
        while (attachments.size() > 30) {
            attachments.remove(attachments.size() - 1);
        }

        message.setText(buildText(sonarqubeMessage));
        message.setAttachments(attachments);

        return message;
    }

    private String buildText(SonarqubePayload sonarqubeMessage) {
        try {
            return String.format("质量域 is **%s** on project: [%s](%s)",
                    sonarqubeMessage.getStatus(),
                    sonarqubeMessage.getProject().getName(),
                    "http://sonar.wanda-group.net/dashboard?id=" + URLEncoder.encode(sonarqubeMessage.getProject().getKey(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // ignore excpetion
            return null;
        }
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

    private String buildText(SonarqubePayload.Condition condition) {
        return String.format("%s %s %s **[%s]**",
                condition.getMetric(),
                condition.getOperator(),
                condition.getErrorThreshold(),
                "NO_VALUE".equals(condition.getStatus()) ? "NA" : condition.getValue());
    }

    @Override
    public boolean ignoreMessage(SonarqubePayload sonarqubeMessage) {
        return false;
    }
}
