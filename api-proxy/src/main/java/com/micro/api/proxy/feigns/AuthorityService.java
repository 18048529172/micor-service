package com.micro.api.proxy.feigns;

import com.micro.data.authority.dto.QueryRoleDTO;
import com.micro.web.response.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/3 17:10
 */
@FeignClient(value  = "micor-api-authority")
public interface AuthorityService {

    /**
     * 查询角色列表
     * @param queryRole
     * @return
     */
    @PostMapping("/api/authority/role/list")
    Response findRolelist(@RequestBody QueryRoleDTO queryRole);



}
