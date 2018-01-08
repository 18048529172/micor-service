package com.micro.api.eureka.microapieureka.dao;

import com.micro.api.eureka.microapieureka.entity.EurekaConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/27 10:57
 */
public interface EurekaConfigDAO extends JpaRepository<EurekaConfig,Long> {
}
