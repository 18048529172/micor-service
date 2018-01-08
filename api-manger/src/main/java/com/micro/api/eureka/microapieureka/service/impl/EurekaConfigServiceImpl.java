package com.micro.api.eureka.microapieureka.service.impl;

import com.micro.api.eureka.microapieureka.dao.EurekaConfigDAO;
import com.micro.api.eureka.microapieureka.entity.EurekaConfig;
import com.micro.api.eureka.microapieureka.service.EurekaConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/27 10:59
 */
@Service
public class EurekaConfigServiceImpl implements EurekaConfigService {

    @Autowired
    private EurekaConfigDAO eurekaConfigDAO;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public Long add(EurekaConfig eurekaConfig) {
         eurekaConfigDAO.save(eurekaConfig);
         return eurekaConfig.getId();
    }

    @Override
    public void deleteById(Long id) {
        this.eurekaConfigDAO.delete(id);
    }

    @Override
    public List<EurekaConfig> findAll() {
        return eurekaConfigDAO.findAll();
    }
}
