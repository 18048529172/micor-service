package com.micro.data.user.vo;

import java.io.Serializable;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/17 10:50
 */
public class GroupVO implements Serializable {

    private Long id;

    private String name;

    private Long createUserId;

    private String createUserName;

    private Integer maxNumber;

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

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Integer getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(Integer maxNumber) {
        this.maxNumber = maxNumber;
    }
}
