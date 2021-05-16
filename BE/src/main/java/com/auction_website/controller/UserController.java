package com.auction_website.controller;

import com.auction_website.model.User;
import com.auction_website.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.auction_website.security.ValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Author: DungNV
     * Cập nhật thông tin người dùng.
     * @param user
     * @param userId
     */
    @PutMapping("/edit/{userId}")
    public ResponseEntity<Void> updateUser(@RequestBody User user, @PathVariable Integer userId){
        try{
            this.userService.updateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @author PhinNL
     * validate phone duplicate
     */
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

    /**
     * @author PhinNL
     * validate identity duplicate
     */
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

