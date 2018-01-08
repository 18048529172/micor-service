package com.micro.api.eureka.microapieureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MicroApiEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroApiEurekaApplication.class, args);
	}
}
