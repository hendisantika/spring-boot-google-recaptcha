package com.hendisantika.controller;

import com.hendisantika.dto.UserDTO;
import com.hendisantika.entity.User;
import com.hendisantika.service.UserService;
import com.hendisantika.validator.CaptchaValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final CaptchaValidator validator;

    @GetMapping("/register")
    public String registerUser(Model model, @RequestParam(value = "version") String version) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("version", version);
        log.info("version : " + version);
        String path = (version.equals("v2") ? "v2/registerUser" : "v3/registerUser");
        log.info("path : " + path);
        return path;
    }

    @PostMapping
    public String saveUser(
            @ModelAttribute UserDTO user,
            Model model,
            @RequestParam("g-recaptcha-response") String captcha
    ) {
        log.info("version : " + user.getVersion());
//        String path = (user.getVersion().equals("v2") || user.getVersion().equals("v2,")) ? "v2/registerUser" : "v3/registerUser";
//        String path = (version.equals("v2")) ? "v2/registerUser" : "v3/registerUser";
//        log.info("path : " + path);
        if (validator.isValidCaptcha(captcha)) {
            Integer id = userService.createUser(user);
            model.addAttribute("message", "User with id : '" + id + "' Saved Successfully !");
            model.addAttribute("user", new User());
        } else {
            model.addAttribute("message", "Please validate captcha first");
        }

        return "redirect:/users";
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("list", users);
        return "listUsers";
    }
}
