package com.auction_website.service.account_role;

import com.auction_website.repository.AccountRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRoleImpl implements AccountRoleService {
    @Autowired
    private AccountRoleRepository accountRoleRepository;
}
