package com.ajt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ajt"})
public class WebBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebBootApplication.class, args);
    }
}
