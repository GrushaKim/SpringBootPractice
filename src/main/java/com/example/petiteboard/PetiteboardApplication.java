package com.example.petiteboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PetiteboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetiteboardApplication.class, args);
	}

}
