package com.micro.api.token.service.impl;

import com.micro.api.token.service.TokenService;
import com.micro.api.token.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/4 14:02
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 创建一个AccessToken，放入到redis中,key:accessToken,value:appId.
     *
     * 如果appId存在，则直接返回，否则新增一个
     *
     * @param appId
     * @param exTime
     * @param timeUnit @return
     */
    @Override
    public String createAccessToken(String appId, long exTime, TimeUnit timeUnit) {
        String uid = UUID.randomUUID().toString();
        String appIdUid = uid+String.valueOf(System.currentTimeMillis());
        String accessToken = MD5Util.getMD5(appIdUid);
        boolean setSuccess = this.stringRedisTemplate.opsForValue().setIfAbsent(accessToken,appId);
        if(setSuccess){
            this.stringRedisTemplate.expire(accessToken,exTime,timeUnit);
            return accessToken;
        }else{
            return this.createAccessToken(appId,exTime,timeUnit);
        }
    }

    /**
     * 通过accessToken获取appId
     *
     * @param accessToken
     * @return
     */
    @Override
    public String getAppIdByAccessToken(String accessToken) {
        return this.stringRedisTemplate.opsForValue().get(accessToken);
    }


}
