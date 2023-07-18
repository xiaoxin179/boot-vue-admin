package com.xiaoxin.mapper;

import com.xiaoxin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User selectUser(@Param("username") String username, @Param("password") String password);
}
