package com.micro.zuul.server.feign;

import com.micro.web.response.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/4 18:02
 */
@FeignClient(value = "micro-api-token")
public interface TokenService {

    @GetMapping("/api/token/access/{accessToken}")
    public Response getAppIdByAccessToken(@PathVariable("accessToken") String accessToken);

}
