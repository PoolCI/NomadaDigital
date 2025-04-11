package com.NomadaDigital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan(basePackages = {"com.NomadaDigital.persistence.mapper"})
public class NomadaDigitalApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(NomadaDigitalApiApplication.class, args);
	}

}
