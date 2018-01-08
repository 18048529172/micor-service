package com.micro.data.authority.dto;

import com.micro.exception.ExceptionChecks;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 10:15
 */
@ApiModel(value = "新增角色")
public class AddRoleDTO implements Serializable {

    @ApiModelProperty(value = "租户id",required = true)
    private String tenementId;

    @ApiModelProperty(value = "角色名称，不能大于20个字符",required = true)
    private String name;

    @ApiModelProperty(value = "角色编码，长度不能小于4个字符，不能大于8个字符，不能重复",required = true)
    private String code;

    @ApiModelProperty(value = "备注，长度不能大于200",required = true)
    private String memo;

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

    public void check(){
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(this.getName()),"名称不能为空");
        ExceptionChecks.checkArgument(this.getName().length()<=20,"名称不能大于20个字符");
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(this.getTenementId()),"租户id不能为空");
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(this.getCode()),"角色编码不能为空");
        ExceptionChecks.checkArgument((this.getCode().length()<=8 && this.getCode().length()>=4),"角色编码必须大于4个字符，小于8个字符");
        if(StringUtils.isNotBlank(this.getMemo())){
            ExceptionChecks.checkArgument((this.getMemo().length()<=200),"角色备注不能大于200个字符");
        }
    }

}
