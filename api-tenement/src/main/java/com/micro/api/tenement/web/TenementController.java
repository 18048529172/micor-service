package com.micro.api.tenement.web;

import com.micro.api.tenement.service.TenementService;
import com.micro.base.api.data.tenement.dto.AddTenementDTO;
import com.micro.base.web.response.Body;
import com.micro.base.web.response.Response;
import com.micro.base.web.response.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/4 17:39
 */
@RestController
public class TenementController {

    @Autowired
    private TenementService tenementService;
    @Autowired
    private ResponseTemplate responseTemplate;

    @PostMapping("/api/tenement/apply/{loginId}")
    public Response apply(@PathVariable("loginId") String loginId,
                          @RequestBody AddTenementDTO addTenement){
        return this.responseTemplate.doResponse(()->{
            addTenement.setApplyUserLoginId(loginId);
            return Body.create("id",this.tenementService.add(addTenement));
        });
    }

    @GetMapping("/api/tenement/{appId}")
    public Response getTenementByAppId(@PathVariable("appId") String appId){
        return this.responseTemplate.doResponse(()->{
            return Body.create("tenement",this.tenementService.findByAppId(appId));
        });
    }

    @GetMapping("/api/tenement/user/{loginId}")
    public Response getTenementByLoginId(@PathVariable("loginId") String loginId){
        return this.responseTemplate.doResponse(()->{
            return Body.create("tenement",this.tenementService.findByLoginId(loginId));
        });
    }



}
