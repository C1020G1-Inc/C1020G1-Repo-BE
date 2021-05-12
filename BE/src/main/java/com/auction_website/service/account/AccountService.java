package com.auction_website.service.account;

import org.springframework.stereotype.Service;

public interface AccountService {
    void changePassword(Integer accountId , String newPassword) ;
}
