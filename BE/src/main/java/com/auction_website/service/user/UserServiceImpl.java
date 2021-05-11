package com.auction_website.service.user;


import com.auction_website.model.User;
import com.auction_website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAllUser(Pageable pageable) {
        return userRepository.findAllUser(pageable);
    }

    @Override
    public void deleteUserById(Integer idUser) {
        userRepository.deleteUser(idUser);
    }

    @Override
    public void lockUserById(Integer idUser) {
        userRepository.lockUser(idUser);
    }

    @Override
    public void unLockUserById(Integer idUser) {
        userRepository.unLockUser(idUser);
    }

    @Override
    public Page<User> searchUser(String userName, Integer userId, String address, String userEmail, Pageable pageable) {
        return userRepository.searchUser(userName, userId, address, userEmail, pageable);
    }

//    @Override
//    public Page<User> findAll(Pageable pageable) {
//        return userRepository.findAll(pageable);
//    }
}
