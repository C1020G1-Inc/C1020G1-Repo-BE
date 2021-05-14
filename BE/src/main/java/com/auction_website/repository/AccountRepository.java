package com.auction_website.repository;

import com.auction_website.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Transactional
    @Modifying

    @Query(value = " update Account set password = ?2 where email = ?1")
    void changePassword(String accountEmail , String newPassword) ;
}
