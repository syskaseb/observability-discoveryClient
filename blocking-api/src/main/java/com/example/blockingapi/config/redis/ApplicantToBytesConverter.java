package com.example.blockingapi.config.redis;

import com.example.blockingapi.applicant.infrastructure.entity.Applicant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

public class ApplicantToBytesConverter implements Converter<Applicant, byte[]> {
    private final Jackson2JsonRedisSerializer<Applicant> serializer;

    public ApplicantToBytesConverter() {
        serializer = new Jackson2JsonRedisSerializer<>(new ObjectMapper(), Applicant.class);
    }

    @Override
    public byte[] convert(Applicant value) {
        return serializer.serialize(value);
    }
}
