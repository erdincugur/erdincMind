package com.erdinc.mindbehind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan
@EntityScan
public class ErdincMindBehindApp {
    public static void main(String[] args) {
        SpringApplication.run(ErdincMindBehindApp.class, args);

    }
}
