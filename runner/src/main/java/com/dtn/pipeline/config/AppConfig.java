package com.dtn.pipeline.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


/**
 * @author danyls ngongang
 * @Created 09/09/2021-07:29
 * @Project runner
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.dtn.pipeline")
public class AppConfig {
    
}
