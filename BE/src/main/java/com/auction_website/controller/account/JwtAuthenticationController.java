package com.auction_website.controller.account;

import com.auction_website.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;


@RestController
@CrossOrigin("http://localhost:4200")
public class JwtAuthenticationController {



    @Autowired
    public  JavaMailSender mailSender;

    @Autowired
    public AccountService accountService ;

    @GetMapping("/api/recover/{accountEmail}")
    public ResponseEntity<?> mailSender(@PathVariable("accountEmail") String accountEmail) throws MessagingException {


        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 5;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        generatedString += "C10a";

        accountService.changePassword(accountEmail, generatedString);



        MimeMessage message = mailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        String htmlMsg = "<h3>Your new password is <i>"+generatedString+"<i></h3>" +
                "<h2><img src='https://static.tapchitaichinh.vn/images/upload/hoangthuviet/2016_09_19/ban-dau-gia-tsdbgega_WHBQ.jpg'> C10_Auction <h2>";

        message.setContent(htmlMsg, "text/html");

        helper.setTo(accountEmail);

        helper.setSubject("Auction Support Recover Password");


        mailSender.send(message);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
