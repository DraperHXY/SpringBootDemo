package com.draper.config;

import com.draper.service.UserService;
import com.draper.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAppConfig {

    // 方法名相当于 bean 的名字
    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

}
