package com.auction_website.service.email;

import com.auction_website.model.ProductTransaction;

import javax.mail.MessagingException;

public interface EmailService {

    void sendMailToWinner(ProductTransaction productTransaction) throws MessagingException;

    void dummyMail();
}
