package com.micro.api.token.web;

import com.micro.api.token.service.TokenService;
import com.micro.exception.ExceptionChecks;
import com.micro.web.response.Body;
import com.micro.web.response.Response;
import com.micro.web.response.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/4 16:14
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ResponseTemplate responseTemplate;

    @Value("${token.expire.hours}")
    private Integer tokenExpireHours;

    /**
     *  根据appId生成一个token，以后的接口调用统一使用token，而不是传入appid，appId不对外暴露。
     * @param appId
     * @return
     */
    @GetMapping("/api/token/appid/{appId}")
    public Response getAccessToken(@PathVariable("appId") String appId){
        return responseTemplate.doResponse(()->{
            String token = this.tokenService.createAccessToken(appId,tokenExpireHours, TimeUnit.HOURS);
            return Body.create("accessToken",token);
        });
    }

    /**
     *  根据accessToken反查appId，此接口是内部调用不向外暴露。
     *
     * @param accessToken
     * @return
     */
    @GetMapping("/api/token/appid/{accessToken}")
    public Response getAppIdByAccessToken(@PathVariable("accessToken") String accessToken){
        return responseTemplate.doResponse(()->{
            String appId = this.tokenService.getAppIdByAccessToken(accessToken);
            ExceptionChecks.checkLogic(appId != null,"accessToken无效");
            return Body.create("appId",appId);
        });
    }


}
