package com.shanhh.bearychat.config;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
    private String teamId;

    @Data
    @NoArgsConstructor
    public static class Token implements Serializable {
        private String name;
        private String token;
    }

    @Bean
    public TokenManager tokenManager() {
        return new TokenManager(tokens);
    }

    public static class TokenManager {
        private Map<String, Token> maps;

        public TokenManager(List<Token> tokens) {
            ImmutableMap.Builder<String, Token> builder = ImmutableMap.builder();
            Preconditions.checkArgument(!CollectionUtils.isEmpty(tokens), "At least one token should be given");
            tokens.stream().forEach(token -> builder.put(token.getName(), token));
            this.maps = builder.build();
        }

        public String getToken(String name) {
            Token token = this.maps.get(name);
            Preconditions.checkNotNull(token, "token not exsits for %s", name);
            return token.getToken();
        }

    }

}

