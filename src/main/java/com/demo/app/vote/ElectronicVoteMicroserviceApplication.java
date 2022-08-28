package com.demo.app.vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ElectronicVoteMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectronicVoteMicroserviceApplication.class, args);
    }

}
