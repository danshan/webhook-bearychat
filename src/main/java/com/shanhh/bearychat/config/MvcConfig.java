package com.shanhh.bearychat.config;


import com.shanhh.bearychat.core.interceptor.PrometheusInterceptor;

import lombok.Getter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

import io.prometheus.client.Collector;
import io.prometheus.client.hotspot.MemoryPoolsExports;
import io.prometheus.client.hotspot.StandardExports;

/**
 * @author dan
 * @since 2017-03-10 15:12
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public PrometheusInterceptor prometheusInterceptor() {
        return new PrometheusInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(prometheusInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public ExporterRegister exporterRegister() {
        List<Collector> collectors = new ArrayList<>();
        collectors.add(new StandardExports());
        collectors.add(new MemoryPoolsExports());
        ExporterRegister register = new ExporterRegister(collectors);
        return register;
    }

    public static class ExporterRegister {

        @Getter
        private List<Collector> collectors;

        public ExporterRegister(List<Collector> collectors) {
            for (Collector collector : collectors) {
                collector.register();
            }
            this.collectors = collectors;
        }

    }

}
