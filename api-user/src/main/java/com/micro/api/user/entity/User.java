package com.micro.api.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.micro.data.user.dto.AddUserDTO;
import com.micro.data.user.vo.UserVO;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 说明：
 *
 *     用户实体类
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


    /**
     * 租户id
     */
    private String tenementId;

    /**
     * 登录名
     */
    private String loginId;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private Integer sex = 1;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 邮箱状态
     */
    private Boolean emailVerification = false;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 注册ip
     */
    private String registIp;

    /**
     * qq号
     */
    private String qq;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 头像
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String headImage;

    /**
     *  是否删除
     */
    private Boolean isDelete = false;
    /**
     *  是否是激活的
     */
    private Boolean isActivate = false;


    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime = new Date();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Set<Friend> friends = new HashSet<>();

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

    public String getTenementId() {
        return tenementId;
    }

    public void setTenementId(String tenementId) {
        this.tenementId = tenementId;
    }

    public Boolean getEmailVerification() {
        return emailVerification;
    }

    public void setEmailVerification(Boolean emailVerification) {
        this.emailVerification = emailVerification;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRegistIp() {
        return registIp;
    }

    public void setRegistIp(String registIp) {
        this.registIp = registIp;
    }

    public Set<Friend> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friend> friends) {
        this.friends = friends;
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
        this.setTenementId(addUser.getTenementId());
        this.setEmailVerification(false);
        this.setBirthday(addUser.getBirthday());
        this.setRegistIp(addUser.getRegistIp());
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
