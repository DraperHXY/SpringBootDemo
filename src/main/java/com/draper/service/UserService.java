package com.draper.service;

import com.draper.domain.User;

public interface UserService {

    int insert(User user);

    User select(long id);

    User selectByName(String name);

}
