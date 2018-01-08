package com.micro.api.eureka.microapieureka.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.micro.api.eureka.microapieureka.dto.InstanceDTO;
import com.micro.api.eureka.microapieureka.dto.ServiceDTO;
import com.micro.api.eureka.microapieureka.dto.ServiceTree;
import com.micro.api.eureka.microapieureka.entity.EurekaConfig;
import com.micro.api.eureka.microapieureka.service.EurekaConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/27 10:08
 */
@RestController
public class EurekaController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EurekaConfigService eurekaConfigService;


    @GetMapping("/eureka/services")
    public List<ServiceDTO> services(){
        return getAllService();
    }


    @PostMapping("/eureka/tree")
    public List<ServiceTree> tree(){
        List<ServiceTree> serviceTrees = new ArrayList<>();
        ServiceTree serviceTree = new ServiceTree();
        serviceTree.setId(UUID.randomUUID().toString());
        serviceTree.setChecked(false);
        serviceTree.setState("open");
        serviceTree.setText("服务");
        List<ServiceDTO> serviceDTOS = this.getAllService();
        for(ServiceDTO serviceDTO : serviceDTOS){
            ServiceTree cl = new ServiceTree();
            cl.setText(serviceDTO.getName());
            cl.setId(UUID.randomUUID().toString());
            cl.setState("open");
            cl.setAttributes(serviceDTO.getApiDoc());
            serviceTree.addChildren(cl);
        }
        serviceTrees.add(serviceTree);
        return serviceTrees;
    }

    private List<ServiceDTO> getAllService(){
        List<EurekaConfig> eurekaApis = this.builderEurekaApis();
        List<ServiceDTO> serviceInfos = new ArrayList<>();
        for(EurekaConfig EurekaConfig : eurekaApis){
            List<ServiceDTO> serviceDTOS = this.getServiceFromEurekaApi(EurekaConfig.getApiUrl());
            serviceInfos.addAll(serviceDTOS);
        }
        return serviceInfos;
    }



    private List<ServiceDTO> getServiceFromEurekaApi(String eurekaApi) {
        List<ServiceDTO> services = new ArrayList<>();
        JSONObject jsonApi =  this.restTemplate.getForObject(eurekaApi, JSONObject.class);
        JSONObject applications = jsonApi.getJSONObject("applications");
        JSONArray application = applications.getJSONArray("application");
        for(Iterator<?> iterator = application.iterator();iterator.hasNext();){
            JSONObject applicationObject = (JSONObject) iterator.next();
            String name = applicationObject.getString("name");
            ServiceDTO serviceDTO = new ServiceDTO();
            serviceDTO.setName(name);
            JSONArray instances = applicationObject.getJSONArray("instance");
            for(Iterator<?> iteratorInstance = instances.iterator();iteratorInstance.hasNext();){
                JSONObject instanceObject = (JSONObject) iteratorInstance.next();
                String ip = instanceObject.getString("ipAddr");
                int port = instanceObject.getJSONObject("port").getInteger("$");
                InstanceDTO instanceDTO = new InstanceDTO();
                instanceDTO.setIp(ip);
                instanceDTO.setPort(port);
                serviceDTO.addInstanceDTO(instanceDTO);
            }
            services.add(serviceDTO);
        }
        return services;
    }

    private List<EurekaConfig> builderEurekaApis() {
       return this.eurekaConfigService.findAll();
    }


}
