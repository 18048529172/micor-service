package com.micro.api.tenement.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.micro.base.api.data.tenement.dto.AddTenementDTO;
import com.micro.base.api.data.tenement.vo.TenementVO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/4 16:51
 */
@Entity
@Table(name = "micro_tenement")
public class Tenement implements Serializable {

    @Id
    @GeneratedValue
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

    public String getApplyUserLoginId() {
        return applyUserLoginId;
    }

    public void setApplyUserLoginId(String applyUserLoginId) {
        this.applyUserLoginId = applyUserLoginId;
    }

    public void fromAddDTO(AddTenementDTO addTenement){
        this.setActity(false);
        this.setApplyUserLoginId(addTenement.getApplyUserLoginId());
        this.setDelete(false);
        this.setDomain(addTenement.getDomain());
        this.setLog(addTenement.getName());
        this.setName(addTenement.getName());
        this.setAppId(StringUtils.replace(UUID.randomUUID().toString(),"-",""));
    }

    public TenementVO toVO(){
        TenementVO vo = new TenementVO();
        vo.setActity(this.getActity());
        vo.setAppId(this.getAppId());
        vo.setApplyUserLoginId(this.getApplyUserLoginId());
        vo.setCode(this.getCode());
        vo.setCreatedDatetime(this.getCreatedDatetime());
        vo.setDelete(this.getDelete());
        vo.setDomain(this.getDomain());
        vo.setId(this.getId());
        vo.setLog(this.getLog());
        vo.setName(this.getName());
        return vo;
    }

}
