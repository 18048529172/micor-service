package com.micro.api.user.service.impl;

import com.micro.api.user.dao.FriendDAO;
import com.micro.api.user.dao.UserDAO;
import com.micro.api.user.entity.Friend;
import com.micro.api.user.entity.User;
import com.micro.api.user.service.UserService;
import com.micro.data.user.dto.AddUserDTO;
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
        return user.toVO();
    }

    @Override
    public UserVO getByLoginIdAndTenementIdIsNull(String loginId) {
        User user = userDAO.findOneByLoginIdAndTenementIdIsNull(loginId);
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
        if(count == 1) {
            return ;
        }
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
