package com.micro.api.token.service;

import java.util.concurrent.TimeUnit;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/4 13:56
 */
public interface TokenService {

    /**
     * 创建一个AccessToken，放入到redis中,key:accessToken,value:appId.
     * @param appId
     * @return
     */
    String createAccessToken(String appId, long exTime, TimeUnit timeUnit);

    /**
     * 通过accessToken获取appId
     * @param accessToken
     * @return
     */
    String getAppIdByAccessToken(String accessToken);




}
