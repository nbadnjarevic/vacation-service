package com.nbadnjarevic.vacationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VacationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacationServiceApplication.class, args);
	}

}
