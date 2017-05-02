package com.shanhh.bearychat.integration.nexus.bean;

import com.shanhh.bearychat.core.webhook.bean.WebhookPayload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author dan
 * @since 2017-05-02 13:38
 */
@Data
@NoArgsConstructor
public class NexusPayload implements WebhookPayload {
    /*
     * @formatter: on
     {
       "timestamp":"2016-11-14T19:32:13.515+0000",
       "nodeId":"7FFA7361-6ED33978-36997BD4-47095CC4-331356BE",
       "initiator":"anonymous/127.0.0.1",
       "repositoryName":"npm-proxy",
       "action":"CREATED",
       "component":{
          "id":"08909bf0c86cf6c9600aade89e1c5e25",
          "format":"npm",
          "name":"angular2",
          "group":"types",
          "version":"0.0.2"
       }
    }
     * @formatter: off
     */
    private String timestamp;
    private String nodeId;
    private String initiator;
    private String repositoryName;
    private String action;

    private Component component;
    private Asset asset;
    private Repository repository;
    private Audit audit;

    @Data
    @NoArgsConstructor
    public static class Component implements Serializable {
        private String id;
        private String format;
        private String name;
        private String group;
        private String version;
    }

    @Data
    @NoArgsConstructor
    public static class Asset implements Serializable {
        private String id;
        private String format;
        private String name;
    }

    @Data
    @NoArgsConstructor
    public static class Repository implements Serializable {
        private String id;
        private String format;
        private String name;
    }

    @Data
    @NoArgsConstructor
    public static class Audit implements Serializable {
        private String domain;
        private String type;
        private String context;
        private Attributes attributes;

        @Data
        @NoArgsConstructor
        public static class Attributes implements Serializable {
            private String name;
            private String email;
            private String source;
            private String status;
            private String roles;
        }
    }
}
