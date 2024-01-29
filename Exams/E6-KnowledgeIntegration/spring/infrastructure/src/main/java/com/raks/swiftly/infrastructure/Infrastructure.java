package com.raks.swiftly.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.raks.swiftly")
public class Infrastructure {

    public static void main(String[] args) {
        SpringApplication.run(Infrastructure.class, args);
    }

}