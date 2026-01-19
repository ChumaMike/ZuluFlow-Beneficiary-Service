package com.zuluflow.beneficiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing; // <--- Import

@SpringBootApplication
@EnableJpaAuditing
public class BeneficiaryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BeneficiaryServiceApplication.class, args);
    }
}