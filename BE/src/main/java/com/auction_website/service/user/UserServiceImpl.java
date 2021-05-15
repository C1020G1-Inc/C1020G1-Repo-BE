package com.auction_website.service.user;

import com.auction_website.model.User;
import com.auction_website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        StringBuilder birthday = new StringBuilder("");
        birthday.append(user.getBirthday().getYear() + 1900);
        int month = user.getBirthday().getMonth() + 1;
        int date = user.getBirthday().getDate();
        birthday.append(month < 10 ? "-0" + month : "-" + month);
        birthday.append(date < 10 ? "-0" + date : "-" + date);
        userRepository.save(user.getUserName(), birthday.toString(), user.getPhone(), user.getIdentity(), user.getAvatar(), user.getAddress());
    }

    @Override
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public User findByIdentity(String identity) {
        return userRepository.findByIdentity(identity);
    }
}
