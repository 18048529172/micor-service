package com.micro.api.tenement.service.impl;

import com.micro.api.tenement.dao.TenementDAO;
import com.micro.api.tenement.entity.Tenement;
import com.micro.api.tenement.service.TenementService;
import com.micro.data.tenement.dto.AddTenementDTO;
import com.micro.data.tenement.vo.TenementVO;
import com.micro.exception.ExceptionChecks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/4 17:09
 */
@Service
public class TenementServiceImpl implements TenementService {

    @Autowired
    private TenementDAO tenementDAO;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public Long add(AddTenementDTO addTenement) {
        addTenement.check();
        this.logicCheck(addTenement);
        Tenement tenement = new Tenement();
        tenement.fromAddDTO(addTenement);
        this.tenementDAO.save(tenement);
        return tenement.getId();
    }

    @Override
    public TenementVO findByAppId(String appId) {
        Tenement tenement = this.tenementDAO.findOneByAppId(appId);
        ExceptionChecks.checkLogic(tenement != null,"appId不存在");
        return tenement.toVO();
    }

    @Override
    public TenementVO findByLoginId(String loginId) {
        Tenement tenement = this.tenementDAO.findOneByApplyUserLoginId(loginId);
        ExceptionChecks.checkLogic(tenement != null,"用户没有申请");
        return tenement.toVO();
    }

    @Override
    public Boolean answerApplyByLoginId(String loginId) {
        return this.tenementDAO.countByApplyUserLoginIdAndIsDeleteFalse(loginId) > 0;
    }

    private void logicCheck(AddTenementDTO addTenement) {
        //验证此用户是否已经申请
        long countByLoginId = this.tenementDAO.countByApplyUserLoginIdAndIsDeleteFalse(addTenement.getApplyUserLoginId());
        ExceptionChecks.checkLogic(countByLoginId == 0,"不能重复申请");
    }
}
