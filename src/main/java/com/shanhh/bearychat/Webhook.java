package com.shanhh.bearychat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 项目入口
 * @author dan
 * @since 2017-05-01 09:08
 */
@SpringBootApplication
@EnableScheduling
public class Webhook {

    public static void main(String[] args) {
        SpringApplication.run(Webhook.class, args);
    }
}
