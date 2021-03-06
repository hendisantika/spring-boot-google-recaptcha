package com.hendisantika.controller;

import com.hendisantika.entity.User;
import com.hendisantika.service.UserService;
import com.hendisantika.validator.CaptchaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-google-recaptcha
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/10/21
 * Time: 06.01
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CaptchaValidator validator;

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "registerUser";
    }

    @PostMapping
    public String saveUser(
            @ModelAttribute User user,
            Model model,
            @RequestParam("g-recaptcha-response") String captcha
    ) {
        if (validator.isValidCaptcha(captcha)) {
            Integer id = userService.createUser(user);
            model.addAttribute("message", "User with id : '" + id + "' Saved Successfully !");
            model.addAttribute("user", new User());
        } else {
            model.addAttribute("message", "Please validate captcha first");
        }

        return "registerUser";
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("list", users);
        return "listUsers";
    }
}
