package com.shanhh.bearychat.core.openapi;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shanhh.bearychat.core.openapi.bean.BearychatMessage;
import com.shanhh.bearychat.core.openapi.bean.BearychatRequest;
import com.shanhh.bearychat.core.openapi.bean.User;

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
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author dan
 * @since 2017-05-01 09:14
 */
@Component
@Slf4j
public class OpenApiImpl implements OpenApi {

    @Resource
    private HttpClient httpClient;
    private static final Gson GSON = new GsonBuilder()
            .serializeNulls()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .create();

    @Override
    public List<User> userList() throws Exception {
        HttpGet request = new HttpGet("https://api.bearychat.com/v1/user.info");

        String responseContent = get(request);
        System.out.println(responseContent);
        return null;
    }

    @Override
    public BearychatMessage messageCreate(BearychatMessage bearychatMessage) {
        return null;
    }

    private String post(HttpPost request, BearychatRequest bearychatRequest) throws IOException {
        bearychatRequest.setToken("token");
        request.setEntity(new StringEntity(GSON.toJson(bearychatRequest)));
        request.addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpResponse response = httpClient.execute(request);
        return EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
    }

    private String get(HttpGet request) throws Exception {
        URI uri = request.getURI();
        if (StringUtils.isBlank(uri.getQuery())) {
            request.setURI(new URI(uri.toString() + "?token=" + ""));
        } else {
            request.setURI(new URI(uri.toString() + "&token=" + ""));
        }
        request.addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpResponse response = httpClient.execute(request);
        Map content = GSON.fromJson(new InputStreamReader(response.getEntity().getContent()), Map.class);
        return null;
    }

}
