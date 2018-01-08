package com.micro.api.eureka.microapieureka.service;

import com.micro.api.eureka.microapieureka.entity.EurekaConfig;

import java.util.List;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/27 10:57
 */
public interface EurekaConfigService {

    Long add(EurekaConfig eurekaConfig);

    void deleteById(Long id);

    List<EurekaConfig> findAll();

}
