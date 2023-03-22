package com.aguo.handle.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class LocalDateTimeConverterConfig {

    private String pattern = "yyyy-MM-dd HH:mm:ss";

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat(pattern));
        return mapper;
    }
}
