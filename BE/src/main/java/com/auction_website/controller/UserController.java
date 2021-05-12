package com.auction_website.controller;

import com.auction_website.model.User;
import com.auction_website.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("/edit/{userId}")
    public void updateUser(@RequestBody User user, @PathVariable Integer userId){
        userService.updateUser(user);
    }

}
