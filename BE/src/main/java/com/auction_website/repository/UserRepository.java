package com.auction_website.repository;

import com.auction_website.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "Select user.userId,user.userName,user.phone,user.address,account.email,account.logoutTime" +
            " from User user " +
            "left join Account account " +
            "on user.userId=account.user.userId")
    Page<User> findAllUser(Pageable pageable);

    @Query(value = "update Account account set account.enable=0 where account.user.userId = :idUser")
    void deleteUser(Integer idUser);

    @Query(value = "update Account account set account.enable=2 where account.user.userId = :idUser")
    void lockUser(Integer idUser);

    @Query(value = "update Account account set account.enable=1 where account.user.userId = :idUser")
    void unLockUser(Integer idUser);

    @Query(value = "select user.userId,user.userName,user.phone,user.address,account.email,account.logoutTime " +
            "from User user " +
            "join Account account " +
            "on user.userId=account.user.userId " +
            "where (:userName is null or user.userName like %:userName%) and " +
            "(:userId is null or user.userId = :userId) and" +
            "(:address is null or user.address = :userAddress) and " +
            "(:userEmail is null or account.email = :userEmail)")
   Page<User> searchUser(String userName, Integer userId, String address, String userEmail, Pageable pageable );
}

