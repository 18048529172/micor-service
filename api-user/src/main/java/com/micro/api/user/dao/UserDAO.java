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

    User findOneByTenementIdAndLoginId(String tenementId, String loginId);

    long countByTenementIdIsNullAndLoginId(String loginId);

    long countByTenementIdAndLoginId(String tenementId, String loginId);
}
