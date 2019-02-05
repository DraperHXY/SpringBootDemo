package com.draper.service.impl;

import com.draper.config.annotation.Log;
import com.draper.domain.User;
import com.draper.mapper.UserMapper;
import com.draper.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Slf4j
@EnableCaching
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        int flag = userMapper.insert(user);

        log.info("flag = {}", flag);
        return flag;
    }

    @Log("查找")
    @Cacheable("user")
    @Override
    public User select(long id) {
        return userMapper.select(id);
    }

    @Cacheable("user")
    @Override
    public User selectByName(String name) {
        return userMapper.selectByName(name);
    }


}
