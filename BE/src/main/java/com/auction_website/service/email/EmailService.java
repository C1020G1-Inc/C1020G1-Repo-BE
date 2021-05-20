package com.auction_website.service.email;

import com.auction_website.model.Account;
import com.auction_website.model.ProductTransaction;

import javax.mail.MessagingException;

public interface EmailService {

    void sendMailToWinner(ProductTransaction productTransaction);
    void sendEmailNearEndTransaction(ProductTransaction productTransaction);
    void sendEmailLockUserByOverCancelTransaction(Account account);
    void sendEmailCancelTransaction(ProductTransaction productTransaction, int numberCancel);

}
