package com.hendisantika.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-google-recaptcha
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/10/21
 * Time: 05.56
 */
@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String email;
}
