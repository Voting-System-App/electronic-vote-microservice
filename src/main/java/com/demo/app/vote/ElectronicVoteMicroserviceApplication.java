package com.demo.app.vote;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableReactiveMongoAuditing
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "User microservice", version = "1.0", description = "Documentation APIs v1.0"))
public class ElectronicVoteMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectronicVoteMicroserviceApplication.class, args);
    }

}
