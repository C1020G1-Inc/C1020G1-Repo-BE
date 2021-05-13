package com.auction_website.service.account;

import com.auction_website.model.Account;
import java.util.List;

public interface AccountService {
    List<Account> findAllUser();


    void lockUserById(Integer idUser);

    void unLockUserById(Integer idUser);

    List<Account> searchUser(String userName, Integer userId, String address, String userEmail);

    List<Account> getUserByDate(Integer month,Integer year);
}
