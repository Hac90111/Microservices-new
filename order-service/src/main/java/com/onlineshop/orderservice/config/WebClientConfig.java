package com.onlineshop.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean // it will create a bean with a method name
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
