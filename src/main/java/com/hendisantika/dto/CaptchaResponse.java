package com.hendisantika.dto;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-google-recaptcha
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/10/21
 * Time: 05.58
 */
@Data
public class CaptchaResponse {
    private boolean success;
    private String challenge_ts;
    private String hostname;
}
