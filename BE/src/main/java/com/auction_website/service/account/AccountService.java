package com.auction_website.service.account;


import com.auction_website.model.Account;

public interface AccountService {
    void changePassword(String accountEmail , String newPassword) ;

    Account findByAccountName(String name);
    Account findByEmail(String email);
    Account findById(Integer id);
    void save(Account account);
    void logout(Integer id);
}
