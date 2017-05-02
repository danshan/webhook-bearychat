package com.shanhh.bearychat.controller;

import com.shanhh.bearychat.integration.nexus.bean.NexusPayload;
import com.shanhh.bearychat.integration.nexus.service.NexusService;
import com.shanhh.bearychat.integration.sonarqube.bean.SonarqubePayload;
import com.shanhh.bearychat.integration.sonarqube.service.SonarqubeService;

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
    private SonarqubeService sonarqubeService;
    @Resource
    private NexusService nexusService;

    @RequestMapping(value = "sonarqube", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void sonarqube(@RequestBody SonarqubePayload sonarqubeMessage) {
        sonarqubeService.handle(sonarqubeMessage);
    }

    @RequestMapping(value = "nexus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void nexus(@RequestBody NexusPayload nexusPayload) {
        nexusService.handle(nexusPayload);
    }
}
