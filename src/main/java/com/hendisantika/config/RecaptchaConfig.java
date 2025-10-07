package com.hendisantika.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-google-recaptcha
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/7/25
 * Time: 20:10
 */
@Configuration
@Getter
public class RecaptchaConfig {

    @Value("${google.recaptcha.v2.site.key}")
    private String v2SiteKey;

    @Value("${google.recaptcha.v3.site.key}")
    private String v3SiteKey;
}
