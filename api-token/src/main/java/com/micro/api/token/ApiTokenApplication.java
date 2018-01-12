package com.micro.api.token;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * =====================================================================================================================
 *
 * 		此服务的查询频率非常高，所有存储采用redis，部署的时候采用多台服务器集群，防止down掉，导致整个服务接口不可用。
 *
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
public class ApiTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTokenApplication.class, args);
	}
}
