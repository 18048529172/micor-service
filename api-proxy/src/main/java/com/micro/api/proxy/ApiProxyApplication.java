package com.micro.api.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 *    组合基础API
 *
 *
 *@author liwei
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ApiProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiProxyApplication.class, args);
	}
}
