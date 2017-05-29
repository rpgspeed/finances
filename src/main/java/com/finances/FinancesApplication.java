package com.finances;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FinancesApplication {
	public static void main(String[] args) {
		SpringApplication.run(FinancesApplication.class, args);
	}
}
