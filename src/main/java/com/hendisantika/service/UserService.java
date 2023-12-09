package com.hendisantika.service;

import com.hendisantika.dto.UserDTO;
import com.hendisantika.entity.User;
import com.hendisantika.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-google-recaptcha
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/10/21
 * Time: 05.57
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Integer createUser(UserDTO user) {
        User entity = User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
        Integer userId = userRepository.save(entity).getId();
        return userId;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
