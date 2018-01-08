package com.micro.api.authority.api.authority.entity;

import com.micro.base.api.data.authority.dto.AddRoleDTO;
import com.micro.base.api.data.authority.vo.RoleVO;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 10:03
 */
@Entity
@Table(name = "micro_authority_role")
public class Role implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String tenementId;

    private String name;

    private String code;

    private String memo;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "role")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "role")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<RoleResource> roleResources = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "role")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<RolePermission> rolePermissions = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenementId() {
        return tenementId;
    }

    public void setTenementId(String tenementId) {
        this.tenementId = tenementId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<RoleResource> getRoleResources() {
        return roleResources;
    }

    public void setRoleResources(Set<RoleResource> roleResources) {
        this.roleResources = roleResources;
    }

    public Set<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public void fromAddDTO(AddRoleDTO addRole){
        this.setCode(addRole.getCode());
        this.setMemo(addRole.getMemo());
        this.setTenementId(addRole.getTenementId());
        this.setName(addRole.getName());
    }

    public RoleVO toVO(){
        RoleVO roleVO = new RoleVO();
        roleVO.setCode(this.getCode());
        roleVO.setId(this.getId());
        roleVO.setMemo(this.getMemo());
        roleVO.setName(this.getName());
        roleVO.setTenementId(this.getTenementId());
        return roleVO;
    }

}
