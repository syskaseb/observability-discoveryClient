package com.example.discoveryclient.config.redis;

import com.example.discoveryclient.applicant.infrastructure.entity.Applicant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

public class BytesToApplicantConverter implements Converter<byte[], Applicant> {

    private final Jackson2JsonRedisSerializer<Applicant> serializer;

    public BytesToApplicantConverter() {
        serializer = new Jackson2JsonRedisSerializer<>(new ObjectMapper(), Applicant.class);
    }

    @Override
    public Applicant convert(byte[] value) {
        return serializer.deserialize(value);
    }
}