package com.shanhh.bearychat.controller;

import com.shanhh.bearychat.sonarqube.bean.SonarqubeMessage;
import com.shanhh.bearychat.webhook.service.WebhookService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dan
 * @since 2017-05-01 14:11
 */
@RestController
@RequestMapping("webhook")
public class WebhookController {

    @Resource
    private WebhookService webhookService;

    @RequestMapping(value = "sonarqube", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void sonarqube(@RequestBody SonarqubeMessage sonarqubeMessage) {
        webhookService.handle(sonarqubeMessage);
    }

}
