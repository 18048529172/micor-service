package com.micro.api.user.dao;

import com.micro.api.user.entity.User;
import com.micro.data.user.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2017/12/27 22:05
 */
public interface UserDAO extends JpaRepository<User,Long>,JpaSpecificationExecutor<User> {
    /**
     * 通过租户id和登录名称查找一个用户，找不到返回null
     * @param tenementId
     * @param loginId
     * @return
     */
    User findOneByTenementIdAndLoginId(String tenementId, String loginId);

    /**
     *  通过租户id为空和登录名称统计用户个数
     * @param loginId
     * @return
     */
    long countByTenementIdIsNullAndLoginId(String loginId);

    /**
     *  通过租户id和登录名称统计用户个数
     * @param tenementId
     * @param loginId
     * @return
     */
    long countByTenementIdAndLoginId(String tenementId, String loginId);

    /**
     * 通过登录名称和租户id为空查询一个用户，没找到返回null
     * @param loginId
     * @return
     */
    User findOneByLoginIdAndTenementIdIsNull(String loginId);

}
