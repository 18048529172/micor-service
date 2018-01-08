package com.micro.api.authority.api.authority.web;

import com.micro.api.authority.api.authority.service.RoleService;
import com.micro.base.api.data.authority.dto.AddRoleDTO;
import com.micro.base.api.data.authority.dto.QueryRoleDTO;
import com.micro.base.web.response.Body;
import com.micro.base.web.response.Response;
import com.micro.base.web.response.ResponseTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/2 10:53
 */
@Api(tags = "角色API")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResponseTemplate responseTemplate;


    @ApiOperation(value = "查询所有角色")
    @PostMapping("/api/authority/role/list")
    public Response list(@RequestBody @ApiParam(value ="查询参数" )QueryRoleDTO queryRole){
        return this.responseTemplate.doResponse(()->{
            return Body.create("roles",this.roleService.findBy(queryRole));
        });
    }


    @ApiOperation(value = "新增一个角色")
    @PostMapping("/api/authority/role/add")
    public Response add(@RequestBody @ApiParam(value = "新增角色")AddRoleDTO addRoleDTO){
        return this.responseTemplate.doResponse(()->{
            return Body.create("id",this.roleService.add(addRoleDTO));
        });
    }

    @ApiOperation(value = "给用户绑定一个角色")
    @PostMapping("/api/authority/role/bind")
    public Response bindRoleForUser(@RequestParam("roleId") @ApiParam(value = "角色id",required = true) Long roleId,
                                    @RequestParam("userId") @ApiParam(value = "用户id",required = true) String userId){
        return this.responseTemplate.doResponse(()->{
            this.roleService.addUserRole(roleId,userId);
            return null;
        });
    }


    @ApiOperation(value = "删除角色")
    @PostMapping("/api/authority/role/delete")
    public Response deleteById(@RequestParam("roleId") @ApiParam(value = "角色id",required = true) Long roleId){
        return this.responseTemplate.doResponse(()->{
            this.roleService.deleteById(roleId);
            return null;
        });
    }



}
