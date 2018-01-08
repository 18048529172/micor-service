package com.micro.api.proxy.api;

import com.alibaba.fastjson.JSONObject;
import com.micro.api.proxy.feigns.AuthorityService;
import com.micro.api.proxy.feigns.TenementService;
import com.micro.api.proxy.feigns.TokenService;
import com.micro.base.api.data.authority.dto.QueryRoleDTO;
import com.micro.base.api.data.tenement.vo.TenementVO;
import com.micro.base.exception.ExceptionChecks;
import com.micro.base.web.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/3 17:15
 */
@RestController
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private TenementService tenementService;

    @PostMapping(value = "/authority/role/list")
    public Response list(@RequestHeader(value = "accessToken",required = true) String accessToken,
                         @RequestParam(value = "name",required = false) String name,
                         @RequestParam(value = "code",required = false) String code,
                         @RequestParam(value = "userId",required = false) String userId
                         ){
        QueryRoleDTO queryRole = new QueryRoleDTO();
        queryRole.setCode(code);
        queryRole.setName(name);
        queryRole.setUserId(userId);
        queryRole.setTenementId(this.getTenementId(accessToken));
        return this.authorityService.findRolelist(queryRole);
    }

    private String getTenementId(String accessToken) {
        Response response = tokenService.getAppIdByAccessToken(accessToken);
        ExceptionChecks.checkLogic(response.isSuccess(),response.getMeta().getMessage());
        String appId = (String) response.getBody().get("appId");
        ExceptionChecks.checkLogic(StringUtils.isNotBlank(appId),"appId为空");
        response =  tenementService.getTenementByAppId(appId);
        ExceptionChecks.checkLogic(response.isSuccess(),response.getMeta().getMessage());
        TenementVO tenementVO = JSONObject.toJavaObject(JSONObject.parseObject(response.getBody().get("tenement").toString()), TenementVO.class);
        return String.valueOf(tenementVO.getId());
    }

}
