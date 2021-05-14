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
}
