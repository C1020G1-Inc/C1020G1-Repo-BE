package com.auction_website.service.account;

import com.auction_website.model.Account;
import java.util.List;

public interface AccountService {
    List<Account> findAllUser();


    void lockUserById(Integer idUser);

    void unLockUserById(Integer idUser);

    List<Account> searchUser(String userName, Integer userId, String address, String userEmail);

    List<Account> getUserByDate(Integer month,Integer year);

    /**
     * Author: DungNV
     * @param oldEmail
     * @param newEmail
     */
    void updateEmail(String oldEmail, String newEmail);

    /**
     * Author: DungNV
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    Account findByAccountName(String name);
    Account findByEmail(String email);
    Account findById(Integer id);
    void save(Account account);
    void logout(Integer id);
    void changePassword(String accountEmail , String newPassword) ;
}
