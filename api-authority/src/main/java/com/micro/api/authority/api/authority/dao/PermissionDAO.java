package com.micro.api.authority.api.authority.dao;

import com.micro.api.authority.api.authority.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 16:00
 */
public interface PermissionDAO extends JpaRepository<Permission,Long>,JpaSpecificationExecutor<Permission> {
}
