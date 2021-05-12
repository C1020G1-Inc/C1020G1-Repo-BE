package com.auction_website.service.account;

import com.auction_website.model.Account;


public interface AccountService {

    void updateEmail(String oldEmail, String newEmail);

    Account findAccountById(Integer accountId);
}
