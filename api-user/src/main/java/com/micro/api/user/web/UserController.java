package com.micro.api.user.web;

import com.micro.api.user.service.UserService;
import com.micro.data.user.dto.AddUserDTO;
import com.micro.data.user.vo.UserVO;
import com.micro.web.response.Body;
import com.micro.web.response.Response;
import com.micro.web.response.ResponseTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/api/user/add")
    public Response add(@RequestBody AddUserDTO addUser){
        return responseTemplate.doResponse(()->{
            Long userId = this.userService.add(addUser);
            return Body.create("id",userId);
        });
    }

    @ApiOperation(value = "使用用户id获取用户详情")
    @GetMapping("/api/user/{id}")
    public Response getById(@PathVariable("id") @ApiParam(value = "用户id") Long id){
        return responseTemplate.doResponse(()->{
            UserVO userVO = this.userService.getById(id);
            return Body.create("userDetail",userVO);
        });
    }

    @ApiOperation(value = "使用登录名称获取用户详情")
    @GetMapping("/api/user/loginId/{loginId}")
    public Response getByLoginId(@PathVariable("loginId") @ApiParam(value = "登录名称") String loginId){
        return responseTemplate.doResponse(()->{
            UserVO userVO = this.userService.getByLoginIdAndTenementIdIsNull(loginId);
            return Body.create("userDetail",userVO);
        });
    }

    @ApiOperation(value = "使用登录名称获和租户id取用户详情")
    @GetMapping("/api/user/tenement/{tenementId}/loginId/{loginId}")
    public Response getByTenementIdAndLoginId(@PathVariable("tenementId") @ApiParam(value = "租户id") String tenementId,
                                              @PathVariable("loginId") @ApiParam(value = "登录名称") String loginId){
        return responseTemplate.doResponse(()->{
            UserVO userVO = this.userService.getByLoginId(tenementId,loginId);
            return Body.create("userDetail",userVO);
        });
    }


    @ApiOperation(value = "使用用户id删除用户")
    @DeleteMapping("/api/user/{id}")
    public Response deleteById(@PathVariable("id") @ApiParam(value = "用户id") Long id){
        return responseTemplate.doResponse(()->{
            this.userService.deleteById(id);
            return null;
        });
    }


    @ApiOperation(value = "新增朋友")
    @PostMapping("/api/user/friend/{id}/{friend}")
    public Response addFriend(@PathVariable("id") @ApiParam(value = "用户id") Long id,
                              @PathVariable("friend") @ApiParam(value = "朋友id") Long friend){
        return responseTemplate.doResponse(()->{

            this.userService.addFriend(id,friend);
            return null;
        });
    }

    @ApiOperation(value = "删除朋友")
    @DeleteMapping("/api/user/friend/{id}/{friend}")
    public Response deleteFriend(@PathVariable("id") @ApiParam(value = "用户id") Long id,
                              @PathVariable("friend") @ApiParam(value = "朋友id") Long friend){
        return responseTemplate.doResponse(()->{
            this.userService.deleteFriend(id,friend);
            return null;
        });
    }


    @ApiOperation(value = "查询所有朋友")
    @GetMapping("/api/user/friend/{id}")
    public Response findFriend(@PathVariable("id") @ApiParam(value = "用户id") Long id){
        return responseTemplate.doResponse(()->{
            List<UserVO> userVOS = this.userService.findAllFriend(id);
            return Body.create("friends",userVOS);
        });
    }


}
