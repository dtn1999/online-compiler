package com.dtn.pipeline.projectservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author danyls ngongang
 * @Created 18/09/2021-11:36
 * @Project project-service
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.dtn.pipeline.projectservice")
public class AppConfig {


    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .baseUrl("https://api.github.com")
                .build();
    }
}
