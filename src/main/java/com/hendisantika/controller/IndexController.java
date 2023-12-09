package com.hendisantika.controller;

import com.hendisantika.dto.UserDTO;
import com.hendisantika.entity.User;
import com.hendisantika.service.UserService;
import com.hendisantika.validator.CaptchaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-google-recaptcha
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/2/23
 * Time: 21:11
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {
    public static final String SECRET_KEY = "<YOUR_SECRET_KEY>";
    public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    private final CaptchaValidator validator;

    private final UserService userService;

    RestTemplateBuilder builder;

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping(value = "/validation")
    public @ResponseBody String ajax(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("secret", SECRET_KEY);
        map.add("response", token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = builder.build().postForEntity(SITE_VERIFY_URL, request, String.class);

        return response.getBody();
    }

    @PostMapping("/validation2")
    public String submitForm(@RequestParam(name = "g-recaptcha-response") String recaptchaResponse,
                             @ModelAttribute UserDTO user,
                             Model model) {
        // Verify reCAPTCHA response
//        boolean isRecaptchaValid = RecaptchaVerifier.verify(recaptchaResponse);

        if (validator.isValidCaptcha(recaptchaResponse)) {
            Integer id = userService.createUser(user);
            model.addAttribute("message", "User with id : '" + id + "' Saved Successfully !");
            model.addAttribute("user", new User());
        } else {
            model.addAttribute("message", "Please validate captcha first");
        }

//        if (isRecaptchaValid) {
//            // reCAPTCHA verification successful, process the form
//            return "success-page";
//        } else {
//            // reCAPTCHA verification failed, handle accordingly
//            return "error-page";
//        }
        return "redirect:/users";
    }
}
