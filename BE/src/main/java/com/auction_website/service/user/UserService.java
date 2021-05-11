package com.auction_website.service.user;


import com.auction_website.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> findAllUser(Pageable pageable);

    //    Page<User> findAll(Pageable pageable);
    void deleteUserById(Integer idUser);

    void lockUserById(Integer idUser);

    void unLockUserById(Integer idUser);

    Page<User> searchUser(String userName, Integer userId, String address, String userEmail, Pageable pageable);
}
