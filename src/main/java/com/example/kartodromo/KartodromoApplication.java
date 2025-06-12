package com.example.kartodromo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class KartodromoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KartodromoApplication.class, args);
	}

}
