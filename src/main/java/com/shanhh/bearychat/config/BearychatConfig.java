package com.shanhh.bearychat.config;

import com.google.common.collect.Lists;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;

/**
 * @author dan
 * @since 2017-05-01 14:30
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "bearychat")
public class BearychatConfig {

    private List<Token> tokens = Lists.newArrayList();
    private String baseUrl;

    @Data
    @NoArgsConstructor
    public static class Token implements Serializable {
        private String name;
        private String token;
    }

    public String getToken(String name) {
        for (Token token : tokens) {
            if (name.equals(token.getName())) {
                return token.getToken();
            }
        }
        return null;
    }

}
