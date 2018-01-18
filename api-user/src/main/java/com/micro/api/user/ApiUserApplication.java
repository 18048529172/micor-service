package com.micro.api.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 *   启动类
 *
 * @author liwei
 */
@SpringBootApplication
@EnableTransactionManagement
public class ApiUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiUserApplication.class, args);
	}
}
