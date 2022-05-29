package com.ksd.cardvalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.ksd.cardvalidator"})
public class CardValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardValidatorApplication.class, args);
	}

}
