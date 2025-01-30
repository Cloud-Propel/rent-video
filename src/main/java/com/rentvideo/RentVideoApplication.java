package com.rentvideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RentVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentVideoApplication.class, args);
        System.out.println("🚀 RentVideo API is running...");
    }
}
