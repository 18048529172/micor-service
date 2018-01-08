package com.micro.base.api.data.user.dto;

import com.micro.base.exception.ExceptionChecks;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/27 22:07
 */
@ApiModel(value = "新增用户")
public class AddUserDTO implements Serializable {

    @ApiModelProperty(value = "登录名，长度不能超过6个字符",required = true)
    private String loginId;

    @ApiModelProperty(value = "姓名，长度不能超过4个字符",required = true)
    private String name;

    @ApiModelProperty(value = "密码，长度不能小于6个字符，不能超过20个字符",required = true)
    private String password;

    @ApiModelProperty(value = "性别，1：男，2：女",required = false)
    private Integer sex = 1;

    @ApiModelProperty(value = "邮件",required = true)
    private String email;

    @ApiModelProperty(value = "qq",required = false)
    private String qq;

    @ApiModelProperty(value = "手机",required = false)
    private String mobile;

    @ApiModelProperty(value = "头像",required = false)
    private String headImage;

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

    public void check(){
        ExceptionChecks.checkArgument((StringUtils.isNotBlank(this.getLoginId())),"登录名称不能为空");
        ExceptionChecks.checkArgument((this.getLoginId().length()<=6),"登录名称长度不能大于6个字符");
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(this.getName()),"名称不能为空");
        ExceptionChecks.checkArgument((this.getName().length()<=4),"名称长度不能大于4个字符");
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(this.getPassword()),"密码不能为空");
        ExceptionChecks.checkArgument((this.getPassword().length()>=6 && this.getPassword().length() <= 20),"密码长度必须大于等于6位字符小于等于20个字符");
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(this.getEmail()),"电子邮件不能为空");
    }


}
