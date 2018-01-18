package com.micro.api.user.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author liwei
 */
@NamedEntityGraph(name = "join.fetch.friend",
        attributeNodes = @NamedAttributeNode("friend"))
@Entity
@Table(name = "micro_friend")
public class Friend implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private User friend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Friend friend = (Friend) o;

        return getId().equals(friend.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
