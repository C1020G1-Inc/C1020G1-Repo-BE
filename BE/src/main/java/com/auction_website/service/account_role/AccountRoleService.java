package com.auction_website.service.account_role;

import com.auction_website.model.AccountRole;

import java.util.List;

public interface AccountRoleService {
    List<AccountRole> findAllByAccountId(Integer accountId);
}
