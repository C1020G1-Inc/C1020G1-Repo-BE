package com.auction_website.repository;

import com.auction_website.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query(value = "select * from account where account_name = ?1", nativeQuery = true)
    Account getAccountByAccountName(String accountName);

    @Query(value = "select * from account where account_email = ?1", nativeQuery = true)
    Account getAccountByEmail(String email);
}
