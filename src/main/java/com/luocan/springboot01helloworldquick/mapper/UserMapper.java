package com.luocan.springboot01helloworldquick.mapper;

import com.luocan.springboot01helloworldquick.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    @Insert("insert into user (password,name,token,gmt_create,gmt_modified) values (#{password},#{name},#{Token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
    @Select("select * from user where name = #{name}")
    User findByName(@Param("name") String name);

}
