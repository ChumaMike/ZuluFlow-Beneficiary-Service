package com.zuluflow.ledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
public class LedgerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LedgerServiceApplication.class, args);
	}

}
