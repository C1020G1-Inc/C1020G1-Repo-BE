package com.auction_website.repository;

import com.auction_website.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;





@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Query(value = "insert into `user`(user_name,user_birthday,user_phone,user_identity," +
            "user_avatar,user_address) values(?1,?2,?3,?4,?5,?6)",nativeQuery = true)
    void save(String accountName, String birthday, String phone, String identity, String avatar, String address);

    @Query(value = "select * from `user` where user_phone = ?1",nativeQuery = true)
    User findByPhone(String phone);

    @Query(value = "select * from `user` where user_identity = ?1",nativeQuery = true)
    User findByIdentity(String identity);
}

