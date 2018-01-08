package com.micro.api.authority.api.authority.web;

import com.micro.api.authority.api.authority.dao.jpaspecification.PermissionSpecification;
import com.micro.api.authority.api.authority.service.PermissionService;
import com.micro.base.api.data.authority.dto.AddPermissionDTO;
import com.micro.base.api.data.authority.dto.QueryPremissionDTO;
import com.micro.base.web.response.Body;
import com.micro.base.web.response.Response;
import com.micro.base.web.response.ResponseTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 16:00
 */
@Api(value = "权限API",tags = "权限API")
@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ResponseTemplate responseTemplate;

    @ApiOperation(value = "新增权限")
    @PostMapping("/api/authority/permission/add")
    public Response add(
            @RequestBody @ApiParam(value = "新增权限")AddPermissionDTO addPermission){
        return this.responseTemplate.doResponse(()->{
            return Body.create("id",this.permissionService.add(addPermission));
        });
    }


    @ApiOperation(value = "查询权限")
    @PostMapping("/api/authority/permission/list")
    public Response list(@RequestBody @ApiParam(value = "查询权限")QueryPremissionDTO queryPremission){
        return this.responseTemplate.doResponse(()->{
            return Body.create("items",this.permissionService.findBy(new PermissionSpecification(queryPremission)));
        });
    }

    @ApiOperation(value = "绑定权限到角色")
    @PostMapping("/api/authority/permission/bindrole")
    public Response list(@RequestParam("roleId")@ApiParam(value = "角色id",required = true) Long roleId,
                         @RequestParam("permissionId")@ApiParam(value = "权限id",required = true) Long permissionId){
        return this.responseTemplate.doResponse(()->{
            this.permissionService.bindToRole(permissionId,roleId);
            return null;
        });
    }



    @ApiOperation(value = "删除权限")
    @PostMapping("/authority/permission/delete")
    public Response deleteById( @RequestParam("id")@ApiParam(value = "权限id",required = true) Long permissionId){
        return this.responseTemplate.doResponse(()->{
            this.permissionService.deleteById(permissionId);
            return null;
        });
    }







}
