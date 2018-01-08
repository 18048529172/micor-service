package com.micro.api.proxy.feigns;

import com.micro.base.web.response.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/4 18:17
 */
@FeignClient(value = "micro-api-tenement")
public interface TenementService {

    @GetMapping("/api/tenement/{appId}")
    Response getTenementByAppId(@PathVariable("appId") String appId);

}
