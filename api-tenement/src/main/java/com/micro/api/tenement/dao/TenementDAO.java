package com.micro.api.tenement.dao;

import com.micro.api.tenement.entity.Tenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/4 17:03
 */
public interface TenementDAO extends JpaRepository<Tenement,Long>,JpaSpecificationExecutor<Tenement> {
    long countByApplyUserLoginIdAndIsDeleteFalse(String applyUserLoginId);

    Tenement findOneByAppId(String appId);

    Tenement findOneByApplyUserLoginId(String loginId);

}
