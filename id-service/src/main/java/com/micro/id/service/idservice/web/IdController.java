package com.micro.id.service.idservice.web;

import com.micro.id.service.idservice.utils.IdGenerator;
import com.micro.web.response.Body;
import com.micro.web.response.Response;
import com.micro.web.response.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdController {

    @Autowired
    private ResponseTemplate responseTemplate;


    @GetMapping("/api/idservice/get")
    public Response getId(){
        return this.responseTemplate.doResponse(()->{
            IdGenerator idGenerator = new IdGenerator(0,0);
            return Body.create("id",idGenerator.nextId());
        });
    }

}
