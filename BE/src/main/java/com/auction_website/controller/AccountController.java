package com.auction_website.controller;

import com.auction_website.model.Account;
import com.auction_website.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")

public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/admin/user-list", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAllUser() {
        try {
            List<Account> userList = accountService.findAllUser();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


    @PutMapping("/admin/lock-user/{idUser}")
    public ResponseEntity<Void> lockUserById(@PathVariable Integer idUser) {
        accountService.lockUserById(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/admin/unlock-user/{idUser}")
    public ResponseEntity<Void> unLockUserById(@PathVariable Integer idUser) {
        accountService.unLockUserById(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/search-user")
    public ResponseEntity<List<Account>> searchUser(@RequestParam String userName, @RequestParam Integer userId,
                                                    @RequestParam String address, @RequestParam String userEmail) {
        try {
            if (userName.equals("undefined")) {
                userName = null;
            }
            if (address.equals("undefined")) {
                address = null;
            }
            if (userEmail.equals("undefined")) {
                userEmail = null;
            }
            if (userId == 0) {
                userId = null;
            }
            System.out.println(userName);
            System.out.println(userId);
            System.out.println(userEmail);
            System.out.println(userId);
            List<Account> userList = accountService.searchUser(userName, userId, address, userEmail);
            System.out.println(userList);
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


    }

    @GetMapping("/admin/user-chart")
    public ResponseEntity<List<Account>> getUserByDate(
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "year", required = false) Integer year) {
        try {
            if (month == 0) {
                month = null;
            }
            List<Account> accountList = accountService.getUserByDate(month, year);
            return new ResponseEntity<>(accountList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
