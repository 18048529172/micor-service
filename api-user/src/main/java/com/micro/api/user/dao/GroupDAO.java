package com.micro.api.user.dao;

import com.micro.api.user.entity.Friend;
import com.micro.api.user.entity.Group;
import com.micro.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 说明：
 *
 * @author liw@suncd.com
 * @date 2018/1/17 9:52
 */
public interface GroupDAO extends JpaRepository<Group,Long>,JpaSpecificationExecutor<Group> {

    long countByUserAndName(User user, String name);
}
