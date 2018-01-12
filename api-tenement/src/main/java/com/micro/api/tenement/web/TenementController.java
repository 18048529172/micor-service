package com.micro.api.tenement.web;

import com.micro.api.tenement.service.TenementService;
import com.micro.data.tenement.dto.AddTenementDTO;
import com.micro.web.response.Body;
import com.micro.web.response.Response;
import com.micro.web.response.ResponseTemplate;
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

    /**
     *  某用户申请成为一个租户
     *
     *     创建自己的一个组织。
     *
     * @param loginId
     * @param addTenement
     * @return
     */
    @PostMapping("/api/tenement/apply/{loginId}")
    public Response apply(@PathVariable("loginId") String loginId,
                          @RequestBody AddTenementDTO addTenement){
        return this.responseTemplate.doResponse(()->{
            addTenement.setApplyUserLoginId(loginId);
            return Body.create("id",this.tenementService.add(addTenement));
        });
    }

    /**
     *   根据一个 appId 查询一个租户
     *
     * @param appId
     * @return
     */
    @GetMapping("/api/tenement/{appId}")
    public Response getTenementByAppId(@PathVariable("appId") String appId){
        return this.responseTemplate.doResponse(()->{
            return Body.create("tenement",this.tenementService.findByAppId(appId));
        });
    }

    /**
     * 通过用户查询申请的租户
     *
     * @param loginId
     * @return
     */
    @GetMapping("/api/tenement/user/{loginId}")
    public Response getTenementByLoginId(@PathVariable("loginId") String loginId){
        return this.responseTemplate.doResponse(()->{
            return Body.create("tenement",this.tenementService.findByLoginId(loginId));
        });
    }

    /**
     *
     * 判断用户是否已经申请
     *
     * @param loginId
     * @return
     */
    @GetMapping("/api/tenement/user/apply/exist/{loginId}")
    public Response hasApply(@PathVariable("loginId") String loginId){
        return this.responseTemplate.doResponse(()->{
            return Body.create("result",this.tenementService.answerApplyByLoginId(loginId));
        });
    }





}
