package com.auction_website.service.account;

import com.auction_website.model.Account;
import com.auction_website.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void changePassword(String accountEmail, String newPassword) {
        accountRepository.changePassword(accountEmail, newPassword);
    }

    @Override
    public Account findByAccountName(String accountName) {
        return accountRepository.findByAccountName(accountName);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findById(email);
    }

    @Override
    public Account findById(Integer id) {
        return accountRepository.findByAccountId(id);
    }

    @Override
    public void save(Account account) {
        account.setLogoutTime(new Timestamp(System.currentTimeMillis()));
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account.getAccountName(),account.getPassword(),account.getEmail(),account.isEnable(),
                account.getLogoutTime(),account.getUser().getUserId());
    }

    @Override
    public void logout(Integer id) {
        accountRepository.logout(new Timestamp(System.currentTimeMillis()),id);
    }
}
