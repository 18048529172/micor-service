package com.micro.api.authority.api.authority.dao;

import com.micro.api.authority.api.authority.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 10:12
 */
public interface RoleDAO extends JpaRepository<Role,Long>,JpaSpecificationExecutor<Role> {
    List<Role> findByTenementId(String tenementId);
}
