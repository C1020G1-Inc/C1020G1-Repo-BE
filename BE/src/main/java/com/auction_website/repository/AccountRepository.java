package com.auction_website.repository;

import com.auction_website.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    /**
     * Author: DungNV
     * @param oldEmail
     * @param newEmail
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE account" +
            " SET account_email = ?2" +
            " WHERE account_email = ?1" , nativeQuery = true)
    void updateEmail(String oldEmail, String newEmail);

    /**
     * Author: DungNV
     * @param accountId
     * @return
     */
    @Query(value = "SELECT *" +
            " FROM account" +
            " WHERE account_id = ?1", nativeQuery = true)
    Account findAccountByAccountId(Integer accountId);
}

