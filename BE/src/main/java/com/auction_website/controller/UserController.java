package com.auction_website.controller;

import com.auction_website.model.User;
import com.auction_website.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")

public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/user-list", method = RequestMethod.GET)
    public ResponseEntity<Page<User>> getAllUser(@PageableDefault(size = 1) Pageable pageable) {
        try {
            Page<User> userList = userService.findAllUser(pageable);
            return new ResponseEntity<>(userList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @PutMapping("/admin/delete-user/{idUser}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer idUser) {
        userService.deleteUserById(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/admin/lock-user/{idUser}")
    public ResponseEntity<Void> lockUserById(@PathVariable Integer idUser) {
        userService.lockUserById(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/admin/lock-user/{idUser}")
    public ResponseEntity<Void> unLockUserById(@PathVariable Integer idUser) {
        userService.unLockUserById(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/search-user")
    public ResponseEntity<Page<User>> searchUser(@PageableDefault(size = 1) Pageable pageable,
                                                 @RequestParam String userName,
                                                 @RequestParam Integer userId,
                                                 @RequestParam String address,
                                                 @RequestParam String userEmail) {
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
        Page<User> userList = userService.searchUser(userName, userId, address, userEmail, pageable);
        return new ResponseEntity<>(userList, HttpStatus.OK);

    }


}

