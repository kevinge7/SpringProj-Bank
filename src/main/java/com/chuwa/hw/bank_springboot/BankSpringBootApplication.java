package com.chuwa.hw.bank_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BankSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankSpringBootApplication.class, args);
	}

}
