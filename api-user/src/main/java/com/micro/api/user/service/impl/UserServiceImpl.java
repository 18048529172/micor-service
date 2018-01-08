package com.micro.api.user.service.impl;

import com.micro.api.user.dao.UserDAO;
import com.micro.api.user.entity.User;
import com.micro.api.user.service.UserService;
import com.micro.base.api.data.user.dto.AddUserDTO;
import com.micro.base.api.data.user.vo.UserVO;
import com.micro.base.exception.ExceptionChecks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/27 22:09
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public Long add(AddUserDTO addUserDTO) {
        addUserDTO.check();
        User user = new User();
        user.fromAdd(addUserDTO);
        userDAO.save(user);
        return user.getId();
    }

    @Override
    public UserVO getById(Long id) {
        User user =  this.userDAO.findOne(id);
        ExceptionChecks.checkLogic((user != null),"用户id："+id+"，不存在");
        return user.toVO();
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void deleteById(Long id) {
        User user = this.userDAO.findOne(id);
        ExceptionChecks.checkLogic((user != null),"用户id："+id+"，不存在");
        user.setDelete(true);
    }
}
