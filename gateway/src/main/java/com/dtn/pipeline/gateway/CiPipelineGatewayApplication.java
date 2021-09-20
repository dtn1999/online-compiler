package com.dtn.pipeline.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "com.dtn.pipeline")
public class CiPipelineGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CiPipelineGatewayApplication.class, args);
    }

}
