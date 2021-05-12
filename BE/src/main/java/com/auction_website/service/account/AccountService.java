package com.auction_website.service.account;

import com.auction_website.model.Account;
import com.auction_website.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {
    List<Account> findAllUser();

    void deleteUserById(Integer idUser);

    void lockUserById(Integer idUser);

    void unLockUserById(Integer idUser);

    List<Account> searchUser(String userName, Integer userId, String address, String userEmail, Pageable pageable);
}
