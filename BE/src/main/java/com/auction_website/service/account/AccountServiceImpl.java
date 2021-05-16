package com.auction_website.service.account;

import com.auction_website.model.Account;
import com.auction_website.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @author PhinNL
     * find account by account name
     */
    @Override
    public void changePassword(String accountEmail, String newPassword) {
        accountRepository.changePassword(accountEmail, newPassword);
    }

    @Override
    public Account findByAccountName(String accountName) {
        return accountRepository.findByAccountName(accountName);
    }

    /**
     * @author PhinNL
     * find account by email
     */
    @Override
    public Account findByEmail(String email) {
        return accountRepository.findById(email);
    }

    /**
     * @author PhinNL
     * find account by id
     */
    @Override
    public Account findById(Integer id) {
        return accountRepository.findByAccountId(id);
    }

    /**
     * @author PhinNL
     * save account (register)
     */
    @Override
    public void save(Account account) {
        account.setLogoutTime(new Timestamp(System.currentTimeMillis()));
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account.getAccountName(),account.getPassword(),account.getEmail(),account.isEnable(),
                account.getLogoutTime(),account.getUser().getUserId());
    }

    /**
     * @author PhinNL
     * update logout time
     */
    @Override
    public void logout(Integer id) {
        accountRepository.logout(new Timestamp(System.currentTimeMillis()),id);
    }

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
    public List<Account> searchUser(String userName, String address, String userEmail) {
        return accountRepository.searchUser(userName, address, userEmail);
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
