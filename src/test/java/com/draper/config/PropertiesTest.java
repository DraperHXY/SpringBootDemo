package com.draper.config;

import com.draper.Application;
import com.draper.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PropertiesTest {

    @Autowired
    private Properties properties;

    @Autowired
    private Person person;

    @Autowired
    private ApplicationContext ctx;

    @Test
    public void contextLoads() {
        Assert.assertEquals("draper\\n", properties.getName());
    }

    @Test
    public void testPerson() {
        log.info(person.toString());
    }

    @Test
    public void testApplicationContext() {
        log.info(String.valueOf(ctx.getBeanDefinitionCount()));
        for (String beanDefinitionName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

    @Test
    public void testContainBean() {
        Assert.assertEquals(true, ctx.containsBean("userService"));
    }
}

