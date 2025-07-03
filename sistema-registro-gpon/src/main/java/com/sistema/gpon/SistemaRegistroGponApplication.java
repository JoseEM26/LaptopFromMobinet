package com.sistema.gpon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaRegistroGponApplication {

	public static void main(String[] args) {
	    com.sistema.gpon.config.DotenvLoader.loadEnv();
		SpringApplication.run(SistemaRegistroGponApplication.class, args);
	}

}
