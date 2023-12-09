package com.hendisantika.validator;

import com.hendisantika.dto.CaptchaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-google-recaptcha
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/10/21
 * Time: 05.59
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CaptchaValidator {

    private final RestTemplate restTemplate;
    @Value("${google.recaptcha.v2.secret.key}")
    private String V2_SECRET_KEY;
    @Value("${google.recaptcha.v3.secret.key}")
    private String V3_SECRET_KEY;

    public boolean isValidCaptcha(String captcha) {
        String url = "https://www.google.com/recaptcha/api/siteverify";
//        String params = "?secret=6LdzW5EUAAAAAK-i3g0r8ok09NFbQzEjwfGd016u&response=" + captcha;
        String params = "?secret=" + V2_SECRET_KEY + "&response=" + captcha;
        String completeUrl = url + params;
        CaptchaResponse resp = restTemplate.postForObject(completeUrl, null, CaptchaResponse.class);
        log.info("captcha: " + captcha);
        log.info("V2_SECRET_KEY: " + V2_SECRET_KEY);
        log.info("V3_SECRET_KEY: " + V3_SECRET_KEY);
        log.info("Response: " + resp);
        return resp.isSuccess();
    }
}
