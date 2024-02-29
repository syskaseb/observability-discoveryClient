package com.example.blockingapi.config.redis;

import com.example.blockingapi.application.Application;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

public class BytesToApplicationConverter implements Converter<byte[], Application> {

    private final Jackson2JsonRedisSerializer<Application> serializer;

    public BytesToApplicationConverter() {
        serializer = new Jackson2JsonRedisSerializer<>(new ObjectMapper(), Application.class);
    }

    @Override
    public Application convert(byte[] value) {
        return serializer.deserialize(value);
    }
}