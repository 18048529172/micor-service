package com.micro.api.user.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/17 9:48
 */
@Entity
@Table(name = "micro_user_group")
public class UserGroup implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")

    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
