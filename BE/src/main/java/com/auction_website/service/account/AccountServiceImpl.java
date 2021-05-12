package com.auction_website.service.account;

import com.auction_website.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void changePassword(Integer accountId, String newPassword) {
        accountRepository.changePassword(accountId , newPassword);
    }
}
