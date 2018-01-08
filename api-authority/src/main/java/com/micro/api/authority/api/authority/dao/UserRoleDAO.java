package com.micro.api.authority.api.authority.dao;

import com.micro.api.authority.api.authority.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 11:05
 */
public interface UserRoleDAO extends JpaRepository<UserRole,Long>,JpaSpecificationExecutor<UserRole> {
}
