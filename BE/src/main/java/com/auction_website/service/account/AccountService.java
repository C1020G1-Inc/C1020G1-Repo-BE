package com.auction_website.service.account;

import com.auction_website.model.Account;


public interface AccountService {

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
