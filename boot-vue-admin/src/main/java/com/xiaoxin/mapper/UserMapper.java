package com.xiaoxin.mapper;

import com.xiaoxin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User selectUser(@Param("username") String username, @Param("password") String password);

    User selectUserByUsername(User user);

    int save(User user);
}
