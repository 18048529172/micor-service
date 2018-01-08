package com.micro.api.authority.api.authority.dao;

import com.micro.api.authority.api.authority.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/3 11:08
 */
public interface RolePermissionDAO  extends JpaRepository<RolePermission,Long>,JpaSpecificationExecutor<RolePermission> {
}
