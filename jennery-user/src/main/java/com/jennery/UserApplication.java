package com.jennery;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 博客用户系统
 *
 * @author MirAcle
 * Created at 2020-03-29 23:11
 */
@MapperScan("com.jennery.user.mapper")
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {

        SpringApplication.run(UserApplication.class, args);
    }
}
