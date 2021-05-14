package com.auction_website.service.user;

import com.auction_website.model.User;
import com.auction_website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
}
