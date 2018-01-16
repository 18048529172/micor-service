package com.micro.api.user.dao;

import com.micro.api.user.entity.Friend;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedEntityGraph;
import java.util.List;

public interface FriendDAO extends JpaRepository<Friend,Long>,JpaSpecificationExecutor<Friend> {

    @Query(countQuery = "select count(id) from #{#entityName} where user.id=:id")
    Long countByUserId(@Param("id") Long id);

    @EntityGraph(value = "join.fetch.friend",type = EntityGraph.EntityGraphType.LOAD)
    @Query(value = "select friend from #{#entityName} friend  where friend.user.id=?1")
    List<Friend> findByUserId(Long id);

    @Query(countQuery = "select count(id) from #{#entityName} where user.id=?1 and friend.id=?2")
    long countByUserIdAndFriendId(Long userId, Long friend);

    @Modifying(clearAutomatically = true)
    @Query(value = "delete from #{#entityName} where user.id=?1 and friend.id=?2")
    void deleteByUserIdAndFriendId(Long userId, Long friend);

}
