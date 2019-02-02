package com.draper.mapper;


import com.draper.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (name,age) VALUES(#{name},#{age})")
    int insert(User user);

}
