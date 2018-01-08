package com.micro.base.api.data.tenement.dto;

import com.micro.base.exception.ExceptionChecks;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/4 17:07
 */
public class AddTenementDTO implements Serializable {

    private String name;

    private String log;

    private String applyUserLoginId;

    private String domain;

    public String getApplyUserLoginId() {
        return applyUserLoginId;
    }

    public void setApplyUserLoginId(String applyUserLoginId) {
        this.applyUserLoginId = applyUserLoginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void check(){
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(this.getName()),"名称不能为空");
        ExceptionChecks.checkArgument(this.getName().length()<=200,"名称不能大于200个字符");
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(this.getLog()),"log不能为空");
        ExceptionChecks.checkArgument(StringUtils.isNotBlank(this.getApplyUserLoginId()),"申请人不能为空");
    }

}
