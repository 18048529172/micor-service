package com.micro.api.authority.api.authority.service;

import com.micro.api.authority.api.authority.entity.Role;
import com.micro.base.api.data.authority.dto.AddRoleDTO;
import com.micro.base.api.data.authority.dto.QueryRoleDTO;
import com.micro.base.api.data.authority.vo.RoleVO;

import java.util.List;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 10:13
 */
public interface RoleService {

    Long add(AddRoleDTO role);

    List<RoleVO> findBy(QueryRoleDTO queryRole);

    void addUserRole(Long roleId,String userId);

    void deleteById(Long id);



}
