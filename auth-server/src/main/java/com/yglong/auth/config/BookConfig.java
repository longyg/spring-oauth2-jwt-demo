package com.yglong.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfig {
    @Bean
    @ConfigurationProperties("test.book")
    public Book book() {
        return new Book();
    }
}
