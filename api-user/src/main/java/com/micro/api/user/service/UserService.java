package com.micro.api.user.service;

import com.micro.data.user.dto.AddUserDTO;
import com.micro.data.user.vo.UserVO;

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
