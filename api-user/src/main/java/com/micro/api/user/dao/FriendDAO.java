package com.micro.api.user.dao;

import com.micro.api.user.entity.Friend;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedEntityGraph;
import java.util.List;

/**
 * @author liwei
 */
public interface FriendDAO extends JpaRepository<Friend,Long>,JpaSpecificationExecutor<Friend> {
    /**
     *  通过用户id统计朋友个数
     * @param id
     * @return
     */
    @Query(countQuery = "select count(id) from #{#entityName} where user.id=:id")
    Long countByUserId(@Param("id") Long id);

    /**
     *  通过用户id查询朋友列表
     * @param id
     * @return
     */
    @EntityGraph(value = "join.fetch.friend",type = EntityGraph.EntityGraphType.LOAD)
    @Query(value = "select friend from #{#entityName} friend  where friend.user.id=?1")
    List<Friend> findByUserId(Long id);

    /**
     *  通过用户id和朋友id统计个数
     * @param userId
     * @param friend
     * @return
     */
    @Query(countQuery = "select count(id) from #{#entityName} where user.id=?1 and friend.id=?2")
    long countByUserIdAndFriendId(Long userId, Long friend);

    /**
     *  删除一个用户的一个朋友
     * @param userId
     * @param friend
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from #{#entityName} where user.id=?1 and friend.id=?2")
    void deleteByUserIdAndFriendId(Long userId, Long friend);

}
