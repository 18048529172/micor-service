package com.micro.api.authority.api.authority.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/3 9:45
 */
@Entity
@Table(name = "micro_authority_role_permission")
public class RolePermission implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id")
    private Permission permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

}
