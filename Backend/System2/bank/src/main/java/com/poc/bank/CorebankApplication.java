package com.poc.bank;

import com.poc.bank.entity.Card;
import com.poc.bank.respository.CardRepository;
import com.poc.bank.util.PinHashUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class CorebankApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorebankApplication.class, args);
	}
	@Bean
	public CommandLineRunner seedData(CardRepository cardRepo) {
		return args -> {
			if (cardRepo.count() == 0) {
				cardRepo.save(new Card(
						"4123456789012345",
						PinHashUtil.sha256("1234"),
						"Alice",
						new BigDecimal("1000.00")
				));
				cardRepo.save(new Card(
						"4123000000000001",
						PinHashUtil.sha256("0000"),
						"Bob",
						new BigDecimal("50.00")
				));
			}
		};
	}
}
