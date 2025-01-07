package com.example.blockingapi.config.redis;

import com.example.blockingapi.applicant.infrastructure.entity.Applicant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration defaults = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(5));

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaults)
                .build();
    }

    @Bean
    public RedisTemplate<String, Applicant> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Applicant> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        return template;
    }

    @Bean
    ApplicantToBytesConverter applicantToBytesConverter() {
        return new ApplicantToBytesConverter();
    }

    @Bean
    BytesToApplicantConverter bytesToApplicantConverter() {
        return new BytesToApplicantConverter();
    }

    @Bean
    ApplicationToBytesConverter applicationToBytesConverter() {
        return new ApplicationToBytesConverter();
    }

    @Bean
    BytesToApplicationConverter bytesToApplicationConverter() {
        return new BytesToApplicationConverter();
    }

    @Bean
    JobOfferToBytesConverter jobOfferToBytesConverter() {
        return new JobOfferToBytesConverter();
    }

    @Bean
    BytesToJobOfferConverter bytesToJobOfferConverter() {
        return new BytesToJobOfferConverter();
    }
}