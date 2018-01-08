package com.micro.api.user.web;

import com.micro.api.user.service.UserService;
import com.micro.base.api.data.user.dto.AddUserDTO;
import com.micro.base.api.data.user.vo.UserVO;
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
 * @date 2017/12/27 22:17
 */
@Api(value = "用户服务",tags = {"用户服务API"})
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseTemplate responseTemplate;

    @ApiOperation(value = "新增用户")
    @PostMapping("/user/add")
    public Response add(@RequestBody AddUserDTO addUser){
        return responseTemplate.doResponse(()->{
            Long userId = this.userService.add(addUser);
            return Body.create("id",userId);
        });
    }

    @ApiOperation(value = "使用用户id获取用户详情")
    @GetMapping("/user/{id}")
    public Response getById(@PathVariable("id") @ApiParam(value = "用户id") Long id){
        return responseTemplate.doResponse(()->{
            UserVO userVO = this.userService.getById(id);
            return Body.create("userDetail",userVO);
        });
    }


    @ApiOperation(value = "使用用户id删除用户")
    @DeleteMapping("/user/{id}")
    public Response deleteById(@PathVariable("id") @ApiParam(value = "用户id") Long id){
        return responseTemplate.doResponse(()->{
            this.userService.deleteById(id);
            return null;
        });
    }




}
