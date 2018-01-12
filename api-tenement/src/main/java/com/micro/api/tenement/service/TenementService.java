package com.micro.api.tenement.service;

import com.micro.data.tenement.dto.AddTenementDTO;
import com.micro.data.tenement.vo.TenementVO;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/4 17:05
 */
public interface TenementService {

    Long add(AddTenementDTO addTenement);

    TenementVO findByAppId(String appId );

    TenementVO findByLoginId( String loginId );

    Boolean answerApplyByLoginId( String loginId );

}
