
package com.smarttravel.bookingservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "userWebClient")
    public WebClient userWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    @Bean(name = "notificationWebClient")
    public WebClient notificationWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8086")
                .build();
    }

    @Bean(name = "paymentWebClient")
    public WebClient paymentWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8085")
                .build();
    }
}

