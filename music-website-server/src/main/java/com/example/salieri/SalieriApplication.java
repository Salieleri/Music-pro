package com.example.salieri;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.salieri.dao")
@SpringBootApplication
public class SalieriApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalieriApplication.class, args);
    }

}
