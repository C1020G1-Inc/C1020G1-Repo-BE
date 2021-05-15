package com.auction_website.repository;

import com.auction_website.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer> {

    /**
     * Author: DungNV
     *
     * @param oldEmail
     * @param newEmail
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE account" +
            " SET account_email = ?2" +
            " WHERE account_email = ?1", nativeQuery = true)
    void updateEmail(String oldEmail, String newEmail);

    /**
     * Author: DungNV
     *
     * @param accountId
     * @return
     */
    @Query(value = "SELECT *" +
            " FROM account" +
            " WHERE account_id = ?1", nativeQuery = true)
    Account findAccountByAccountId(Integer accountId);

    @Transactional
    @Modifying
    @Query(value = " update Account set password = ?2 where email = ?1")
    void changePassword(String accountEmail, String newPassword);

    @Query(value = "select * from account where account_name = ?1", nativeQuery = true)
    Account findByAccountName(String accountName);

    @Query(value = "select * from account where account_email = ?1", nativeQuery = true)
    Account findById(String email);

    @Query(value = "select * from account where account_id = ?1", nativeQuery = true)
    Account findByAccountId(Integer id);

    @Modifying
    @Query(value = "update account set account_logout_time = ?1 where account_id = ?2", nativeQuery = true)
    void logout(Timestamp logoutTime, Integer id);

    @Modifying
    @Query(value = "insert into account(account_name,account_password,account_email,account_enable," +
            "account_logout_time,user_id) values(?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    void save(String accountName, String password, String email, boolean enable, Timestamp logoutTime, Integer userId);

}

