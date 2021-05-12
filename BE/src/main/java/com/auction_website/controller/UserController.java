package com.auction_website.controller;

import com.auction_website.model.Account;
import com.auction_website.model.User;
import com.auction_website.security.ValidationResponse;
import com.auction_website.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/guest/exist/phone")
    public ResponseEntity<ValidationResponse> findEmailExist(@RequestParam String phone){
        try {
            User user = userService.findByPhone(phone);
            ValidationResponse validationResponse = new ValidationResponse();
            if (user != null){
                validationResponse.setPhoneDuplicate(user.getPhone());
            }
            return new ResponseEntity<>(validationResponse, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/guest/exist/identity")
    public ResponseEntity<ValidationResponse> findByIdentity(@RequestParam String identity){
        try {
            User user = userService.findByIdentity(identity);
            ValidationResponse validationResponse = new ValidationResponse();
            if (user != null){
                validationResponse.setIdentityDuplicate(user.getIdentity());
            }
            return new ResponseEntity<>(validationResponse, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
