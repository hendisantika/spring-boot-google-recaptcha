package com.hendisantika.repository;

import com.hendisantika.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-google-recaptcha
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/10/21
 * Time: 05.56
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
