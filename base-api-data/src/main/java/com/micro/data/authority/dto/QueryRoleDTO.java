package com.micro.data.authority.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 11:17
 */
@ApiModel
public class QueryRoleDTO implements Serializable{

    @ApiModelProperty(value = "租户id",required = true)
    private String tenementId;

    @ApiModelProperty(value = "角色名称，模糊查询",required = false)
    private String name;

    @ApiModelProperty(value = "角色编码",required = false)
    private String code;

    @ApiModelProperty(value = "用户id",required = false)
    private String userId;

    public String getTenementId() {
        return tenementId;
    }

    public void setTenementId(String tenementId) {
        this.tenementId = tenementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
