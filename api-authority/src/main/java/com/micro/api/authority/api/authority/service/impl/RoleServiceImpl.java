package com.micro.api.authority.api.authority.service.impl;

import com.micro.api.authority.api.authority.dao.RoleDAO;
import com.micro.api.authority.api.authority.dao.UserRoleDAO;
import com.micro.api.authority.api.authority.dao.jpaspecification.RoleSpecification;
import com.micro.api.authority.api.authority.entity.Role;
import com.micro.api.authority.api.authority.entity.UserRole;
import com.micro.api.authority.api.authority.service.RoleService;
import com.micro.data.authority.dto.AddRoleDTO;
import com.micro.data.authority.dto.QueryRoleDTO;
import com.micro.data.authority.vo.RoleVO;
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
 * @date 2018/1/2 10:41
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public Long add(AddRoleDTO addRoleDTO) {
        addRoleDTO.check();
        Role role = new Role();
        role.fromAddDTO(addRoleDTO);
        this.roleDAO.save(role);
        return role.getId();
    }

    @Override
    public List<RoleVO> findBy(QueryRoleDTO queryRole) {
        RoleSpecification roleSpecification = new RoleSpecification(queryRole);
        List<Role> roles = this.roleDAO.findAll(roleSpecification);
        return this.toListVO(roles);
    }



    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void addUserRole(Long roleId, String userId) {
        //验证角色是否存在
        //验证用户是否存在
        //验证用户和角色是否在一个租户下
        Role role = this.roleDAO.findOne(roleId);
        ExceptionChecks.checkArgument((role != null),"角色不存在");
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUserId(userId);
        this.userRoleDAO.save(userRole);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void deleteById(Long id) {
        this.roleDAO.delete(id);
    }

    private List<RoleVO> toListVO(List<Role> roles) {
        List<RoleVO> vos = new ArrayList<>();
        for(Role role : roles){
            vos.add(role.toVO());
        }
        return vos;
    }

}
