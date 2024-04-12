package com.bci.apiuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bci.apiuser.repository")
public class ApiUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiUserApplication.class, args);
    }

}
