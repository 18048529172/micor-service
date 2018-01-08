package com.micro.base.api.data.authority.dto;

import com.micro.base.exception.ExceptionChecks;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 16:28
 */
@ApiModel(value = "新增权限")
public class AddPermissionDTO implements Serializable {

    @ApiModelProperty(value = "租户id",required = true)
    private String tenementId;

    @ApiModelProperty(value = "权限名称，最长为20个字符",required = true)
    private String name;

    @ApiModelProperty(value = "角色编码，最小不能小于4个字符，最大不超过8个字符",required = true)
    private String code;

    @ApiModelProperty(value = "备注，最大200个字符",required = false)
    private String memo;

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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


    public void check(){
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(tenementId),"租户id不能为空");
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(name),"权限名称不能为空");
        ExceptionChecks.checkArgument(this.name.length()<=20,"名称不能超过20个字符");
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(this.code),"权限编码不能为空");
        ExceptionChecks.checkArgument(this.code.length()<=8 && this.code.length()>=4,"权限编码最小不能小于4个字符，最大不超过8个字符");
        if(StringUtils.isNotBlank(this.getMemo())){
            ExceptionChecks.checkArgument(this.memo.length()<=200,"权限备注不能超过200个字符");
        }
    }

}
