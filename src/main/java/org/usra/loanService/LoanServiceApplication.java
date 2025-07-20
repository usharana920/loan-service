package org.usra.loanService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class LoanServiceApplication {

	public static void main(String[] args) {
		log.info("Loan-application started.");
		SpringApplication.run(LoanServiceApplication.class, args);
	}

}
