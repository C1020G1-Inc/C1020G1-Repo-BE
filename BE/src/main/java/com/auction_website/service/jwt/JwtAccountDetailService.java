package com.auction_website.service.jwt;

import com.auction_website.model.Account;
import com.auction_website.model.AccountRole;
import com.auction_website.security.AccountPrincipal;
import com.auction_website.service.account.AccountService;
import com.auction_website.service.account_role.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class JwtAccountDetailService implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRoleService accountRoleService;

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        Account account = accountService.getAccountByName(accountName);
        if(account == null) {
            throw new UsernameNotFoundException("Account not found with account name: " + accountName);
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String role;
        for (AccountRole accountRole: accountRoleService.findAllByAccountId(account.getAccountId())){
            role = accountRole.getRole().getName();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
            grantedAuthorities.add(grantedAuthority);
        }
        return new AccountPrincipal(account.getAccountName(),account.getPassword(),account.isEnable(),grantedAuthorities);
    }
}
