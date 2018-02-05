package com.micro.api.token;

import com.micro.api.token.service.TokenService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TokenServiceTest extends ApiTokenApplicationTests{

    @Autowired
    private TokenService tokenService;

    @Test
    public void test(){
        String appId = UUID.randomUUID().toString();
        String token = tokenService.createAccessToken(appId,1, TimeUnit.MINUTES);
        Assert.isTrue(appId.equals(tokenService.getAppIdByAccessToken(token)),"");
        while(true){
            System.out.println(tokenService.getAppIdByAccessToken(token));
        }
    }
}
