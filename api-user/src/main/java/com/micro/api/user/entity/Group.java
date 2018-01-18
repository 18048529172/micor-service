package com.micro.api.user.entity;

import com.micro.data.user.dto.AddGroupDTO;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 说明：
 *
 *     群
 *
 * @author liw@suncd.com
 * @date 2018/1/17 9:43
 */
@Entity
@Table(name = "micro_group")
public class Group implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private User createBy;

    private Integer maxNumber = 500;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "group")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<UserGroup> userGroups = new HashSet<>();

    private Date createTime;


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

    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    public Integer getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(Integer maxNumber) {
        this.maxNumber = maxNumber;
    }

    public Set<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void fromDTO(AddGroupDTO addGroup){
        this.setName(addGroup.getName());
        this.setCreateTime(new Date());
    }

}
