package com.example.blockingapi.config.redis;

import com.example.blockingapi.applicant.infrastructure.entity.Applicant;
import com.example.blockingapi.joboffer.JobOffer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

public class JobOfferToBytesConverter implements Converter<JobOffer, byte[]> {
    private final Jackson2JsonRedisSerializer<JobOffer> serializer;

    public JobOfferToBytesConverter() {
        serializer = new Jackson2JsonRedisSerializer<>(new ObjectMapper(), JobOffer.class);
    }

    @Override
    public byte[] convert(JobOffer value) {
        return serializer.serialize(value);
    }
}
