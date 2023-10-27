//package com.example.medatlas.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class JacksonConfig {
//
//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.findAndRegisterModules(); // Активирует модули Jackson (например, JavaTimeModule для работы с датами)
//        return objectMapper;
//    }
//}