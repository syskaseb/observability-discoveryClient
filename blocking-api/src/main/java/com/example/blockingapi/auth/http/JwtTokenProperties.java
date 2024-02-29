package com.example.blockingapi.auth.http;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
record JwtTokenProperties(String secret, Long expiration) {
}
