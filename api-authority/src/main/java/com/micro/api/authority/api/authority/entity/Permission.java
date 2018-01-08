package com.micro.api.authority.api.authority.entity;

import com.micro.data.authority.dto.AddPermissionDTO;
import com.micro.data.authority.vo.PermissionVO;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 9:56
 */
@Entity
@Table(name = "micro_authority_pemission")
public class Permission implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String tenementId;

    private String name;

    private String code;

    private String memo;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "permission")
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

    public Set<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public PermissionVO toVO(){
        PermissionVO permissionVO = new PermissionVO();
        permissionVO.setCode(this.getCode());
        permissionVO.setId(this.getId());
        permissionVO.setMemo(this.getMemo());
        permissionVO.setName(this.getName());
        permissionVO.setTenementId(this.getTenementId());
        return permissionVO;
    }

    public void fromAddDTO(AddPermissionDTO addPermission) {
        this.setCode(addPermission.getCode());
        this.setMemo(addPermission.getMemo());
        this.setName(addPermission.getName());
        this.setTenementId(addPermission.getTenementId());
    }
}
