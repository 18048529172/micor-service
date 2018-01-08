package com.micro.data.tenement.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/4 17:33
 */
public class TenementVO implements Serializable {

    private Long id;

    private String name;

    private String code;

    private String appId;

    private String applyUserLoginId;

    private String log;

    private Boolean isActity = false;

    private Boolean isDelete = false;

    private String domain;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createdDatetime = new Date();

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getApplyUserLoginId() {
        return applyUserLoginId;
    }

    public void setApplyUserLoginId(String applyUserLoginId) {
        this.applyUserLoginId = applyUserLoginId;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Boolean getActity() {
        return isActity;
    }

    public void setActity(Boolean actity) {
        isActity = actity;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }
}
