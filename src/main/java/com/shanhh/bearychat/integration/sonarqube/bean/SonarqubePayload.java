package com.shanhh.bearychat.integration.sonarqube.bean;

import com.shanhh.bearychat.core.webhook.bean.WebhookPayload;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author dan
 * @since 2017-05-01 14:42
 */
@Data
@NoArgsConstructor
@ToString
public class SonarqubePayload implements WebhookPayload {
    /*
    @formatter:on
    {
        "analysedAt": "2016-11-18T10:46:28+0100",
        "project": {
            "key": "org.sonarqube:example",
            "name": "Example"
        },
        "properties": {
        },
        "qualityGate": {
            "conditions": [
                {
                    "errorThreshold": "1",
                    "metric": "new_security_rating",
                    "onLeakPeriod": true,
                    "operator": "GREATER_THAN",
                    "status": "OK",
                    "value": "1"
                },
                {
                    "errorThreshold": "1",
                    "metric": "new_reliability_rating",
                    "onLeakPeriod": true,
                    "operator": "GREATER_THAN",
                    "status": "OK",
                    "value": "1"
                },
                {
                    "errorThreshold": "1",
                    "metric": "new_maintainability_rating",
                    "onLeakPeriod": true,
                    "operator": "GREATER_THAN",
                    "status": "OK",
                    "value": "1"
                },
                {
                    "errorThreshold": "80",
                    "metric": "new_coverage",
                    "onLeakPeriod": true,
                    "operator": "LESS_THAN",
                    "status": "NO_VALUE" // ERROR / OK / WARN / NO_VALUE
                }
            ],
            "name": "SonarQube way",
            "status": "OK" // ERROR / OK / WARN
        },
        "status": "SUCCESS",
        "taskId": "AVh21JS2JepAEhwQ-b3u"
    }
    @formatter:off
     */

    private String analysedAt;
    private Project project;
    private QualityGate qualityGate;
    private String status;
    private String taskId;

    @Data
    @NoArgsConstructor
    public static class Project implements Serializable {
        private String key;
        private String name;
    }

    @Data
    @NoArgsConstructor
    public static class QualityGate implements Serializable {
        private List<Condition> conditions;
        private String name;
        private String status;
    }

    @Data
    @NoArgsConstructor
    public static class Condition implements Serializable {
        private String errorThreshold;
        private String metric;
        private boolean onLeakPeriod;
        private String operator;
        private String status;
        private String value;
    }
}
