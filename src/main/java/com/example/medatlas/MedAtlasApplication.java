package com.example.medatlas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedAtlasApplication {
    private static final Logger logger = LoggerFactory.getLogger(MedAtlasApplication.class);

    public static void main(String[] args) {
        logger.info("Запуск приложения");
        SpringApplication.run(MedAtlasApplication.class, args);
    }
}