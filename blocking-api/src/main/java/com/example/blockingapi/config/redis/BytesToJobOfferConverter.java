package com.example.blockingapi.config.redis;

import com.example.blockingapi.joboffer.JobOffer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

public class BytesToJobOfferConverter implements Converter<byte[], JobOffer> {

    private final Jackson2JsonRedisSerializer<JobOffer> serializer;

    public BytesToJobOfferConverter() {
        serializer = new Jackson2JsonRedisSerializer<>(new ObjectMapper(), JobOffer.class);
    }

    @Override
    public JobOffer convert(byte[] value) {
        return serializer.deserialize(value);
    }
}