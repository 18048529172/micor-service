package com.micro.api.eureka.microapieureka.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/27 10:55
 */
@Entity
@Table(name = "micro_eurekaConfig")
public class EurekaConfig implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String apiUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

}
