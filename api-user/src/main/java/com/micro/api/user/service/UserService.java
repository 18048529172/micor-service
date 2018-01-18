package com.micro.api.user.service;

import com.micro.data.user.dto.AddGroupDTO;
import com.micro.data.user.dto.AddUserDTO;
import com.micro.data.user.vo.GroupVO;
import com.micro.data.user.vo.UserVO;

import java.util.List;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/27 22:06
 */
public interface UserService {



    //=================用户====================//
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
     *  通过登录名称，查询的用户
     * @param loginId
     * @return
     */
    UserVO getByLoginIdAndTenementIdIsNull(String loginId);

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

    //=================朋友====================//

    /**
     * 统计朋友个数
     * @param id
     * @return
     */
    Long countFriend(Long id);

    /**
     *  查询所有的朋友
     *
     * @param id
     * @return
     */
    List<UserVO> findAllFriend(Long id);

    /**
     * 新增朋友
     * @param userId
     * @param friend
     */
    void addFriend(Long userId,Long friend);

    /**
     * 删除朋友
     * @param userId
     * @param friend
     */
    void deleteFriend(Long userId,Long friend);

    /**
     *  修改加朋友的个数，只能增多，不能减少
     * @param userId
     * @param friendNumber
     */
    void updateFriendNumber(Long userId,Integer friendNumber);

    //=================群====================//

    /**
     * 新增群
     * @param addGroup
     */
    void createGroup(AddGroupDTO addGroup);

    /**
     * 删除群
     * @param groupId
     * @param groupId
     */
    void deleteGroup(Long groupId);

    /**
     * 修改群名称
     * @param groupId
     * @param name
     */
    void updateGroupName(Long groupId,String name);

    /**
     * 查询群下的所有人
     * @param groupId
     * @return
     */
    List<UserVO> findByGroup(Long groupId);

    /**
     * 添加一个人到群
     * @param userId
     * @param groupId
     */
    void addUserToGroup(Long userId,Long groupId);

    /**
     * 查询自己创建的群
     * @param userId
     * @return
     */
    List<GroupVO> findMyCreateGroup(Long userId);

    /**
     * 查询自己加入的群
     * @param userId
     * @return
     */
    List<GroupVO> findMyJoinGroup(Long userId);

}
