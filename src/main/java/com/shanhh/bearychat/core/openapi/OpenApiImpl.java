package com.shanhh.bearychat.core.openapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.shanhh.bearychat.config.BearychatConfig;
import com.shanhh.bearychat.core.openapi.bean.BearychatMessage;
import com.shanhh.bearychat.core.openapi.bean.BearychatRequest;
import com.shanhh.bearychat.core.openapi.bean.BearychatUser;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

/**
 * @author dan
 * @since 2017-05-01 09:14
 */
@Component
@Slf4j
public class OpenApiImpl implements OpenApi {

    @Resource
    private BearychatConfig bearychatConfig;

    @Resource
    private HttpClient httpClient;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));

    @Override
    public List<BearychatUser> userList(String service) throws Exception {
        HttpGet request = new HttpGet("https://api.bearychat.com/v1/user.list");

        List<BearychatUser> list = get(service, request, new TypeReference<List<BearychatUser>>() {});
        return list;
    }

    @Override
    public BearychatMessage messageCreate(String service, BearychatMessage bearychatMessage) {
        return null;
    }

    private String post(String service, HttpPost request, BearychatRequest bearychatRequest) throws IOException {
        bearychatRequest.setToken(bearychatConfig.getToken(service));
        request.setEntity(new StringEntity(OBJECT_MAPPER.writeValueAsString(bearychatRequest)));
        request.addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpResponse response = httpClient.execute(request);
        return EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
    }

    private <T> T get(String service, HttpGet request, TypeReference typeOfT) throws Exception {
        URI uri = request.getURI();
        if (StringUtils.isBlank(uri.getQuery())) {
            request.setURI(new URI(uri.toString() + "?token=" + bearychatConfig.getToken(service)));
        } else {
            request.setURI(new URI(uri.toString() + "&token=" + ""));
        }
        request.addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpResponse response = httpClient.execute(request);
        return OBJECT_MAPPER.readValue(new InputStreamReader(response.getEntity().getContent()), typeOfT);
    }

}
