package com.micro.api.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.micro.base.api.data.user.dto.AddUserDTO;
import com.micro.base.api.data.user.vo.UserVO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/27 21:51
 */
@Entity
@Table(name = "micro_user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String loginId;

    private String name;

    private String password;

    private Integer sex = 1;

    private String email;

    private String qq;

    private String mobile;

    private String headImage;

    private Boolean isDelete = false;

    private Boolean isActivate = false;


    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Boolean getActivate() {
        return isActivate;
    }

    public void setActivate(Boolean activate) {
        isActivate = activate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void fromAdd(AddUserDTO addUser){
        this.setSex(addUser.getSex());
        this.setQq(addUser.getQq());
        this.setPassword(addUser.getPassword());
        this.setName(addUser.getName());
        this.setMobile(addUser.getMobile());
        this.setLoginId(addUser.getLoginId());
        this.setHeadImage(addUser.getHeadImage());
        this.setEmail(addUser.getEmail());
        this.setDelete(false);
        this.setActivate(true);
        this.setCreateTime(new Date());
    }

    public UserVO toVO(){
        UserVO vo = new UserVO();
        vo.setActivate(this.getActivate());
        vo.setCreateTime(this.getCreateTime());
        vo.setDelete(this.getDelete());
        vo.setEmail(this.getEmail());
        vo.setHeadImage(this.getHeadImage());
        vo.setId(this.getId());
        vo.setLoginId(this.getLoginId());
        vo.setMobile(this.getMobile());
        vo.setName(this.getName());
        vo.setPassword(this.getPassword());
        vo.setQq(this.getQq());
        vo.setSex(this.getSex());
        return vo;
    }


}
