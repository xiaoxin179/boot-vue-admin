package com.xiaoxin.service;

import com.xiaoxin.entity.User;
import com.xiaoxin.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author:XIAOXIN
 * @date:2023/07/19
 **/
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;
    public User login(String username,String password) {
        User user=userMapper.selectUser(username,password);
        return user;
    }


    public boolean register(User user) {
//        查询数据库中是否有这个用户
        User user1=userMapper.selectUserByUsername(user);
        if (user1 ==null ) {
            int res1=userMapper.save(new User(user.getUsername(),user.getPassword()));
            return res1 != 0;
        }
        return false;
    }
}
