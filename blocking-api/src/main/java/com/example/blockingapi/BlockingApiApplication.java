package com.example.blockingapi;

import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RestController;

@EnableRetry
@EnableCaching
@EnableAsync
@EnableNeo4jRepositories
@EnableMongoRepositories
@EnableJpaRepositories
@EnableDiscoveryClient
@ConfigurationPropertiesScan
@EnableConfigurationProperties
@SpringBootApplication
@RequiredArgsConstructor
@RestController
public class BlockingApiApplication implements com.example.blockingapi.greeting.GreetingController {

    private final EurekaClient eurekaClient;
    private final Environment environment;

    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(BlockingApiApplication.class, args);
    }

    @Override
    public String greeting() {
        String msg;
        if (environment != null) {
            int port = Integer.parseInt(environment.getProperty("local.server.port", "0"));
            if (eurekaClient.getApplication(appName) != null) {
                msg = String.format(
                        "Hello from '%s'! Service is running on port %d", eurekaClient.getApplication(appName).getName(), port);
            } else {
                msg = "Could not obtain application name from discovery client";
            }
        } else {
            msg = "Error retrieving port information.";
        }
        return msg;
    }
}