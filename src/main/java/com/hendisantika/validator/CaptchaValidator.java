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
        return isValidCaptcha(captcha, "v2");
    }

    public boolean isValidCaptcha(String captcha, String version) {
        String url = "https://www.google.com/recaptcha/api/siteverify";
        String secretKey = "v3".equalsIgnoreCase(version) ? V3_SECRET_KEY : V2_SECRET_KEY;
        String params = "?secret=" + secretKey + "&response=" + captcha;
        String completeUrl = url + params;
        CaptchaResponse resp = restTemplate.postForObject(completeUrl, null, CaptchaResponse.class);
        log.info("captcha: {}", captcha);
        log.info("version: {}", version);
        log.info("secretKey: {}", secretKey);
        log.info("Response: {}", resp);
        return resp != null && resp.isSuccess();
    }
}
