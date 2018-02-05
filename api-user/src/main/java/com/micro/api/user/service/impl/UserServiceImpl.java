package com.micro.api.user.service.impl;

import com.micro.api.user.dao.FriendDAO;
import com.micro.api.user.dao.GroupDAO;
import com.micro.api.user.dao.UserDAO;
import com.micro.api.user.entity.Friend;
import com.micro.api.user.entity.Group;
import com.micro.api.user.entity.User;
import com.micro.api.user.service.UserService;
import com.micro.data.user.dto.AddGroupDTO;
import com.micro.data.user.dto.AddUserDTO;
import com.micro.data.user.vo.GroupVO;
import com.micro.data.user.vo.UserVO;
import com.micro.exception.ExceptionChecks;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private FriendDAO friendDAO;

    @Autowired
    private GroupDAO groupDAO;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public Long add(AddUserDTO addUserDTO) {
        addUserDTO.check();
        //登录loginId需要验证是否重复
        validate(addUserDTO);
        User user = new User();
        user.fromAdd(addUserDTO);
        userDAO.save(user);
        return user.getId();
    }

    @Override
    public UserVO getByLoginId(String tenementId, String loginId) {
        User user = userDAO.findOneByTenementIdAndLoginId(tenementId,loginId);
        if(user == null){
            return null;
        }
        return user.toVO();
    }

    @Override
    public UserVO getByLoginIdAndTenementIdIsNull(String loginId) {
        User user = userDAO.findOneByLoginIdAndTenementIdIsNull(loginId);
        if(user == null){
            return null;
        }
        return user.toVO();
    }

    @Override
    public Long countFriend(Long id) {
        return friendDAO.countByUserId(id);
    }

    @Override
    public List<UserVO> findAllFriend(Long id) {
        List<UserVO> vos = new ArrayList<>();
        List<Friend> friends = friendDAO.findByUserId(id);
        for(Friend friend : friends){
            vos.add(friend.getFriend().toVO());
        }
        return vos;
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void addFriend(Long userId, Long friend) {
        long count = this.friendDAO.countByUserIdAndFriendId(userId,friend);
        ExceptionChecks.checkLogic(count == 0,"已经添加");
        //验证上限
        User user = this.userDAO.findOne(userId);
        long countFriend = this.countFriend(userId);
        ExceptionChecks.checkLogic(countFriend<user.getMaxFriendNumber(),"添加朋友数已经达到上限");
        ExceptionChecks.checkLogic(count == 0,"已经添加");
        Friend friends = new Friend();
        friends.setFriend(this.userDAO.getOne(friend));
        friends.setUser(this.userDAO.getOne(userId));
        this.friendDAO.save(friends);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void deleteFriend(Long userId, Long friend) {
        this.friendDAO.deleteByUserIdAndFriendId(userId,friend);
    }

    /**
     * 修改加朋友的个数，只能增多，不能减少
     *
     * @param userId
     * @param friendNumber
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void updateFriendNumber(Long userId, Integer friendNumber) {
        ExceptionChecks.checkArgument(friendNumber > 0,"朋友个数不能小于0");
        User user = this.userDAO.findOne(userId);
        ExceptionChecks.checkArgument(user != null,"用户无效");
        Integer maxFriendNumber = user.getMaxFriendNumber();
        ExceptionChecks.checkArgument(friendNumber > maxFriendNumber,"朋友个数不能改小");
        user.setMaxFriendNumber(friendNumber);
    }

    /**
     * 新增群
     *
     * @param addGroup
     */
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void createGroup(AddGroupDTO addGroup) {
        addGroup.check();
        User user = this.userDAO.findOne(addGroup.getCreateUserId());
        ExceptionChecks.checkArgument(user != null,"用户无效");
        //群名称是重复
        long nameCount = groupDAO.countByCreateByAndName(user,addGroup.getName());
        ExceptionChecks.checkArgument(nameCount ==0,"名称重复");
        Group group = new Group();
        group.fromDTO(addGroup);
        group.setCreateBy(user);
        this.groupDAO.save(group);

    }

    /**
     * 删除群
     *
     * @param groupId
     */
    @Override
    public void deleteGroup(Long groupId) {

    }

    /**
     * 修改群名称
     *
     * @param groupId
     * @param name
     */
    @Override
    public void updateGroupName(Long groupId, String name) {

    }

    /**
     * 查询群下的所有人
     *
     * @param groupId
     * @return
     */
    @Override
    public List<UserVO> findByGroup(Long groupId) {
        return null;
    }

    /**
     * 添加一个人到群
     *
     * @param userId
     * @param groupId
     */
    @Override
    public void addUserToGroup(Long userId, Long groupId) {

    }

    /**
     * 查询自己创建的群
     *
     * @param userId
     * @return
     */
    @Override
    public List<GroupVO> findMyCreateGroup(Long userId) {
        return null;
    }

    /**
     * 查询自己加入的群
     *
     * @param userId
     * @return
     */
    @Override
    public List<GroupVO> findMyJoinGroup(Long userId) {
        return null;
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

    private void validate(AddUserDTO addUserDTO) {
        String tenementId = addUserDTO.getTenementId();
        if(StringUtils.isNotBlank(tenementId)){
            //验证在此租户下登录名称是否重复
            long loginIdCount = userDAO.countByTenementIdAndLoginId(tenementId,addUserDTO.getLoginId());
            ExceptionChecks.checkLogic(loginIdCount == 0,"登录名称已经存在");
        }else{
            long loginIdCount = userDAO.countByTenementIdIsNullAndLoginId(addUserDTO.getLoginId());
            ExceptionChecks.checkLogic(loginIdCount == 0,"登录名称已经存在");
        }
    }
}
