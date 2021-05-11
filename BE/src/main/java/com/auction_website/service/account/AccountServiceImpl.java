package com.auction_website.service.account;

import com.auction_website.model.Account;
import com.auction_website.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void updateEmail(String oldEmail, String newEmail) {
        accountRepository.updateEmail(oldEmail, newEmail);
    }
}
