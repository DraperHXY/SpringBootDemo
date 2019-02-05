package com.draper.service;

import com.draper.Application;
import com.draper.domain.User;
import com.draper.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSelect() {
        userService.select(1L);
        userService.select(1L);
        userService.select(1L);
        userService.select(1L);
        userService.select(1L);
        userService.select(1L);
    }
}

