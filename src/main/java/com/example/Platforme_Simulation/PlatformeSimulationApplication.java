package com.example.Platforme_Simulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EntityScan("com.example.Platforme_Simulation.entity")
@EnableJpaRepositories("com.example.Platforme_Simulation.repository")
public class PlatformeSimulationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformeSimulationApplication.class, args);
	}

}
