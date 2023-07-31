package com.xiaoxin.mapper;

import com.xiaoxin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    User selectUser(@Param("username") String username, @Param("password") String password);

    User selectUserByUsername(User user);

    int save(User user);

    List<User> getAllUsers(@Param("name") String name,@Param("phone")String phone,@Param("email")String email);

    void insert(User user);

    void update(User user);

    void deleteById(Integer id);
}
