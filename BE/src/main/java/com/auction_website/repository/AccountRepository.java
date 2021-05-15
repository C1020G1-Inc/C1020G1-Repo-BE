package com.auction_website.repository;

import com.auction_website.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer> {

    /**
     * Author : ThinhHN
     * Get all user
     */
    @Query("Select account from  Account account")
    List<Account> findAllUser();

    /**
     * Author : ThinhHN
     * Lock user by id
     */
    @Transactional
    @Modifying
    @Query(value = "update Account account set account.enable=0 where account.user.userId = :idUser")
    void lockUser(Integer idUser);

    /**
     * Author : ThinhHN
     * Unlock user by id
     */
    @Transactional
    @Modifying
    @Query(value = "update Account account set account.enable=1 where account.user.userId = :idUser")
    void unLockUser(Integer idUser);

    /**
     * Author : ThinhHN
     * Search advance
     */
    @Query(value = "select account" +
            " from Account account " +
            "where (:userName is null or account.user.userName like %:userName%) and " +
            "(:userId is null or account.user.userId = :userId) and" +
            "(:address is null or account.user.address = :address) and " +
            "(:userEmail is null or account.email = :userEmail)")
    List<Account> searchUser(String userName, Integer userId, String address, String userEmail);

    /**
     * Author : ThinhHN
     * Get all user by date
     */
    @Query(value = "select  account" +
            " from Account account" +
            " where ((:month is null ) or ((function('month',account.logoutTime)) = :month)) and" +
            "(function('year',account.logoutTime)=:year )")
    List<Account> getUserByDate(Integer month, Integer year);


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

