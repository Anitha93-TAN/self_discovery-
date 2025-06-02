package com.self_discovery.self_discovery;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing
@SpringBootApplication
public class SelfDiscoveryApplication {

	public static void main(String[] args) {

		SpringApplication.run(SelfDiscoveryApplication.class, args);
	}

}
