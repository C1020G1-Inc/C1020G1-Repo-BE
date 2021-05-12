package com.auction_website.repository;

import com.auction_website.model.Account;
import com.auction_website.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("Select account from  Account account where account.enable =1 or  account.enable =2 ")
    List<Account> findAllUser();

    @Transactional
    @Modifying
    @Query(value = "update Account account set account.enable=0 where account.user.userId = :idUser")
    void deleteUser(Integer idUser);

    @Transactional
    @Modifying
    @Query(value = "update Account account set account.enable=2 where account.user.userId = :idUser")
    void lockUser(Integer idUser);

    @Transactional
    @Modifying
    @Query(value = "update Account account set account.enable=1 where account.user.userId = :idUser")
    void unLockUser(Integer idUser);

    @Query(value = "select account" +
            " from Account account " +
            "where (:userName is null or account.user.userName like %:userName%) and " +
            "(:userId is null or account.user.userId = :userId) and" +
            "(:address is null or account.user.address = :userAddress) and " +
            "(:userEmail is null or account.email = :userEmail)")
    List<Account> searchUser(String userName, Integer userId, String address, String userEmail, Pageable pageable);
}
