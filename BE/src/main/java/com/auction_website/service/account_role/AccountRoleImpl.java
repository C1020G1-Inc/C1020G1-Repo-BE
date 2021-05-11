package com.auction_website.service.account_role;

import com.auction_website.model.AccountRole;
import com.auction_website.repository.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRoleImpl implements AccountRoleService {
    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Override
    public List<AccountRole> findAllByAccountId(Integer accountId) {
        return accountRoleRepository.findAllByAccountId(accountId);
    }
}
