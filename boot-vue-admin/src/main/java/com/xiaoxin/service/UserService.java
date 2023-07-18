package com.xiaoxin.service;

import com.xiaoxin.entity.User;
import com.xiaoxin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:XIAOXIN
 * @date:2023/07/19
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User login(String username,String password) {
        User user=userMapper.selectUser(username,password);
        return user;
    }

}
