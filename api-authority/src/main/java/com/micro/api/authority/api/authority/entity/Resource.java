package com.micro.api.authority.api.authority.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 17:24
 */
@Entity
@Table(name = "micro_authority_resource")
public class Resource implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String tenementId;

    private String name;

    private String openUrl;

    private String memo;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "resource")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<RoleResource> roleResources = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenUrl() {
        return openUrl;
    }

    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTenementId() {
        return tenementId;
    }

    public void setTenementId(String tenementId) {
        this.tenementId = tenementId;
    }

    public Set<RoleResource> getRoleResources() {
        return roleResources;
    }

    public void setRoleResources(Set<RoleResource> roleResources) {
        this.roleResources = roleResources;
    }
}
