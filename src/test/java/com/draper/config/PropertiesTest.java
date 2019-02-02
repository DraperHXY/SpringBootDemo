package com.draper.config;

import com.draper.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PropertiesTest {

    @Autowired
    private Properties properties;

    @Test
    public void contextLoads() {
        Assert.assertEquals("draper", properties.getName());
    }

}

