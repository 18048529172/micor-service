package com.micro.api.eureka.microapieureka.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/27 10:10
 */
public class ServiceDTO implements Serializable {

    private String name;

    private List<InstanceDTO> instances = new ArrayList<>();

    public void addInstanceDTO(InstanceDTO instance){
        this.instances.add(instance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InstanceDTO> getInstances() {
        return instances;
    }

    public void setInstances(List<InstanceDTO> instances) {
        this.instances = instances;
    }

    public String getApiDoc(){
        if(this.instances.isEmpty()){
            return null;
        }
        return "http://"+this.instances.get(0).getIp()+":"+this.instances.get(0).getPort()+"/doc.html";
    }
}
