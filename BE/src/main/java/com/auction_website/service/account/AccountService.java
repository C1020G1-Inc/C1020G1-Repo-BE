package com.auction_website.service.account;


import com.auction_website.model.Account;

import java.util.List;

public interface AccountService {
    void changePassword(String accountEmail , String newPassword) ;

    Account findByAccountName(String name);
    Account findByEmail(String email);
    Account findById(Integer id);
    void save(Account account);
    void logout(Integer id);

    List<Account> findAllUser();


    void lockUserById(Integer idUser);

    void unLockUserById(Integer idUser);

    List<Account> searchUser(String userName, String address, String userEmail);

    List<Account> getUserByDate(Integer month, Integer year);
}
