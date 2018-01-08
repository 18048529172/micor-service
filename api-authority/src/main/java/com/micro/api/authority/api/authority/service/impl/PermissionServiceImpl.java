package com.micro.api.authority.api.authority.service.impl;

import com.micro.api.authority.api.authority.dao.PermissionDAO;
import com.micro.api.authority.api.authority.dao.RoleDAO;
import com.micro.api.authority.api.authority.dao.RolePermissionDAO;
import com.micro.api.authority.api.authority.dao.jpaspecification.PermissionSpecification;
import com.micro.api.authority.api.authority.entity.Permission;
import com.micro.api.authority.api.authority.entity.Role;
import com.micro.api.authority.api.authority.entity.RolePermission;
import com.micro.api.authority.api.authority.service.PermissionService;
import com.micro.data.authority.dto.AddPermissionDTO;
import com.micro.data.authority.vo.PermissionVO;
import com.micro.exception.ExceptionChecks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 16:21
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDAO permissionDAO;
    @Autowired
    private RolePermissionDAO rolePermissionDAO;
    @Autowired
    private RoleDAO roleDAO;

    @Override
    public List<PermissionVO> findBy(PermissionSpecification permissionSpecification) {
        List<PermissionVO> vos = new ArrayList<>();
        List<Permission> permissions =  permissionDAO.findAll(permissionSpecification);
        for(Permission permission : permissions){
           PermissionVO permissionVO =  permission.toVO();
            vos.add(permissionVO);
        }
        return vos;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public Long add(AddPermissionDTO addPermission) {
        addPermission.check();
        Permission permission = new Permission();
        permission.fromAddDTO(addPermission);
        this.permissionDAO.save(permission);
        return permission.getId();
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void bindToRole(Long permissionId, Long roleId) {
        ExceptionChecks.checkArgument(permissionId != null,"权限id不能为空");
        ExceptionChecks.checkArgument(roleId != null,"角色id不能为空");
        Role role = this.roleDAO.findOne(roleId);
        ExceptionChecks.checkArgument(role != null,"角色不存在");
        Permission permission = this.permissionDAO.findOne(permissionId);
        ExceptionChecks.checkArgument(permission != null,"权限不存在");
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermission(permission);
        rolePermission.setRole(role);
        this.rolePermissionDAO.save(rolePermission);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void deleteById(Long id) {
        this.permissionDAO.delete(id);
    }
}
