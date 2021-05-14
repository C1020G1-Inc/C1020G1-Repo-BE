package com.auction_website.service.account;

import com.auction_website.model.Account;
import com.auction_website.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    /**
     * Author : ThinhHN
     * Get all user
     */
    @Override
    public List<Account> findAllUser() {
        return accountRepository.findAllUser();
    }

    /**
     * Author : ThinhHN
     * Lock user by id
     */

    @Override
    public void lockUserById(Integer idUser) {
        accountRepository.lockUser(idUser);
    }

    /**
     * Author : ThinhHN
     * Unlock user by id
     */
    @Override
    public void unLockUserById(Integer idUser) {
        accountRepository.unLockUser(idUser);
    }

    /**
     * Author : ThinhHN
     * Search advance
     */
    @Override
    public List<Account> searchUser(String userName, Integer userId, String address, String userEmail) {
        return accountRepository.searchUser(userName, userId, address, userEmail);
    }

    /**
     * Author : ThinhHN
     * Get all user by date
     */
    @Override
    public List<Account> getUserByDate(Integer month, Integer year) {
        return accountRepository.getUserByDate(month, year);
    }


}
