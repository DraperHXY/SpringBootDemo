package com.draper.mapper;

import com.draper.Application;
import com.draper.config.Properties;
import com.draper.domain.User;
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
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback
    public void testInsert() {

    }

    @Test
    @Transactional
    public void testSelect() {
        User user = new User();
        user.setName("111");
        user.setAge(22);
        userMapper.insert(user);
        user = userMapper.selectByName("111");
        Assert.assertEquals(22l, (long)user.getAge());
    }
}

