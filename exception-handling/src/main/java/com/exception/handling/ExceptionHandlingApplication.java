package com.exception.handling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ExceptionHandlingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExceptionHandlingApplication.class, args);
    }

}
