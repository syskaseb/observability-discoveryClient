package com.example.discoveryclient;

import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.RestController;

@EnableRetry
@EnableCaching
@EnableJpaRepositories
@EnableDiscoveryClient
@SpringBootApplication
@RequiredArgsConstructor
@RestController
public class DiscoveryClientApplication implements GreetingController {

    private final EurekaClient eurekaClient;
    private final Environment environment;

    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryClientApplication.class, args);
    }

    @Override
    public String greeting() {
        if (environment != null) {
            int port = Integer.parseInt(environment.getProperty("local.server.port", "0"));
            return String.format(
                    "Hello from '%s'! Service is running on port %d", eurekaClient.getApplication(appName).getName(), port);
        } else {
            return "Error retrieving port information.";
        }
    }
}