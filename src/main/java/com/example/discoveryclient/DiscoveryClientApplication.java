package com.example.discoveryclient;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class DiscoveryClientApplication implements GreetingController {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private Environment environment;

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