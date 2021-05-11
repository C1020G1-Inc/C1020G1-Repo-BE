package com.auction_website.service.account;


import com.auction_website.model.Account;

public interface AccountService {
    Account getAccountByName(String name);
    Account getAccountByEmail(String email);
}
