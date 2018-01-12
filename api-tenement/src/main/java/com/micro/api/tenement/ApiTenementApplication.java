package com.micro.api.tenement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * =====================================================================================================================
 *    租户服务：
 *
 *        这里来源于多租户的概念
 *
 *        可以理解为一个用户所拥有的资源的集合，已租户的概念来隔离租户资源的边界，所有后续的所有资源都会带有租户的id。
 *
 *        这里的流程是现有用户才有租户，也就是说是用户申请一个资源的集合。
 *
 *        此服务在用户申请成为一个租户的时候，返回给用户一个appId，此id在租户服务中唯一，为用户所有，不能修改。
 *
 *        即：可以通过用户查询租户，再通过租户查询此租户下的所有用户、和其他的资源。
 *
 *
 *
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
public class ApiTenementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTenementApplication.class, args);
	}
}
