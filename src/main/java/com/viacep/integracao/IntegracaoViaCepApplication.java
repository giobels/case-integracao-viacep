package com.viacep.integracao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.viacep.integracao.infrastructure")
public class IntegracaoViaCepApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegracaoViaCepApplication.class, args);
	}

}
