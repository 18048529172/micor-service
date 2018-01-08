package com.micro.api.user.service;

import com.micro.api.user.entity.User;
import com.micro.base.api.data.user.dto.AddUserDTO;
import com.micro.base.api.data.user.vo.UserVO;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/27 22:06
 */
public interface UserService {

    Long add(AddUserDTO addUserDTO);

    UserVO getById(Long id);

    void deleteById(Long id);
}
