package com.micro.api.authority.api.authority.service;

import com.micro.api.authority.api.authority.dao.jpaspecification.PermissionSpecification;
import com.micro.base.api.data.authority.dto.AddPermissionDTO;
import com.micro.base.api.data.authority.vo.PermissionVO;

import java.util.List;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 16:05
 */
public interface PermissionService {

    List<PermissionVO> findBy(PermissionSpecification permissionSpecification);

    Long add(AddPermissionDTO addPermission);

    void bindToRole(Long permissionId,Long roleId);

    void deleteById(Long id);





}
