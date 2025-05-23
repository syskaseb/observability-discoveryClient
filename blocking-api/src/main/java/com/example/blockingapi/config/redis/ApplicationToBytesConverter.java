package com.example.blockingapi.config.redis;

import com.example.blockingapi.applicant.infrastructure.entity.Applicant;
import com.example.blockingapi.application.Application;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

public class ApplicationToBytesConverter implements Converter<Application, byte[]> {
    private final Jackson2JsonRedisSerializer<Application> serializer;

    public ApplicationToBytesConverter() {
        serializer = new Jackson2JsonRedisSerializer<>(new ObjectMapper(), Application.class);
    }

    @Override
    public byte[] convert(Application value) {
        return serializer.serialize(value);
    }
}
