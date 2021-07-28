package com.group4.secbaselinebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication
@MapperScan("com.group4.secbaselinebackend")
@EnableScheduling
public class SecBaselineBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecBaselineBackendApplication.class, args);
    }

}
