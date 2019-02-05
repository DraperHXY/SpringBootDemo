package com.draper.mapper;


import com.draper.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (name,age) VALUES(#{name},#{age})")
    int insert(User user);

    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    @Select("SELECT id,name,age FROM user WHERE id = #{id}")
    User select(long id);


    @Select("SELECT id,name,age FROM user WHERE name = #{name}")
    User selectByName(String name);

}
