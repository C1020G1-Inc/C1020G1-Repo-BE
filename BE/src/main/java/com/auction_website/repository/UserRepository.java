package com.auction_website.repository;

import com.auction_website.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE user u" +
            " SET u.user_name = :#{#user.getUserName()}," +
            " u.user_birthday = :#{#user.getBirthday()}," +
            " u.user_identity = :#{#user.getIdentity()}," +
            " u.user_phone = :#{#user.getPhone()}," +
            " u.user_address = :#{#user.getAddress()}" +
            " WHERE u.user_id = :#{#user.getUserId()}" , nativeQuery = true)
    void updateUser(User user);

}
