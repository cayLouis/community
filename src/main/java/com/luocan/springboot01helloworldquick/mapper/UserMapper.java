package com.luocan.springboot01helloworldquick.mapper;

import com.luocan.springboot01helloworldquick.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified) values (#{Account},#{name},#{Token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
}
