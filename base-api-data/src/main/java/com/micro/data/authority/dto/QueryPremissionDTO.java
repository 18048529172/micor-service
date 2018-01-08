package com.micro.data.authority.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 16:02
 */
@ApiModel(value = "权限查询")
public class QueryPremissionDTO implements Serializable {

    @ApiModelProperty(value = "租户id",required = true)
    private String tenementId;

    @ApiModelProperty(value = "角色id",required = false)
    private Long roleId;

    @ApiModelProperty(value = "权限编码",required = false)
    private String code;

    @ApiModelProperty(value = "用户id",required = false)
    private String userId;

    @ApiModelProperty(value = "权限名称，模糊查询",required = false)
    private String name;

    public String getTenementId() {
        return tenementId;
    }

    public void setTenementId(String tenementId) {
        this.tenementId = tenementId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
