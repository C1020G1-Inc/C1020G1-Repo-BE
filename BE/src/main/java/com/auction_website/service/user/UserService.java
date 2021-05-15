package com.auction_website.service.user;


import com.auction_website.model.User;

public interface UserService {
    void save(User user);
    User findByPhone(String phone);
    User findByIdentity(String identity);
    /**
     * Author: DungNV
     * @param user
     */
    void updateUser(User user);
}
