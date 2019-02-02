package com.draper.service.impl;

import com.draper.domain.User;
import com.draper.mapper.UserMapper;
import com.draper.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
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

}
