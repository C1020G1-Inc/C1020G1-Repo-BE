package com.auction_website.controller;

import com.auction_website.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PutMapping("/{oldEmail}/{newEmail}/update-email/")
    public ResponseEntity<?> updateAccountEmail(@PathVariable("oldEmail") String oldEmail,@PathVariable("newEmail") String newEmail){
        accountService.updateEmail(oldEmail, newEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
