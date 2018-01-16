package com.micro.api.user.service;

import com.micro.data.user.dto.AddUserDTO;
import com.micro.data.user.vo.UserVO;

import java.util.List;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/27 22:06
 */
public interface UserService {

    /**
     * 新增用户
     * @param addUserDTO
     * @return
     */
    Long add(AddUserDTO addUserDTO);

    /**
     * 通过登录名称，查询某租户下的用户
     * @param tenementId
     * @param loginId
     * @return
     */
    UserVO getByLoginId(String tenementId,String loginId);

    /**
     *
     * @return
     */
    Long countFriend(Long id);


    List<UserVO> findAllFriend(Long id);

    void addFriend(Long userId,Long friend);

    /**
     *  通过用户id获取用户信息
     * @param id
     * @return
     */
    UserVO getById(Long id);

    /**
     *  删除用户
     * @param id
     */
    void deleteById(Long id);


}
