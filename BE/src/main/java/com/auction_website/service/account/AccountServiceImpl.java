package com.auction_website.service.account;

import com.auction_website.model.Account;
import com.auction_website.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> findAllUser() {
        return accountRepository.findAllUser();
    }

    @Override
    public void deleteUserById(Integer idUser) {
        accountRepository.deleteUser(idUser);
    }

    @Override
    public void lockUserById(Integer idUser) {
        accountRepository.lockUser(idUser);
    }

    @Override
    public void unLockUserById(Integer idUser) {
        accountRepository.unLockUser(idUser);
    }

    @Override
    public List<Account> searchUser(String userName, Integer userId, String address, String userEmail, Pageable pageable) {
        return accountRepository.searchUser(userName, userId, address, userEmail, pageable);
    }


}
