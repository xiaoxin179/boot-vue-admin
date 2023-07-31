package com.xiaoxin.service;

import cn.hutool.core.util.StrUtil;
import com.xiaoxin.common.exception.CustomerException;
import com.xiaoxin.entity.User;
import com.xiaoxin.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


/**
 * @author:XIAOXIN
 * @date:2023/07/19
 **/
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final String PWD = "123";

    @Autowired
    private UserMapper userMapper;
    public User login(String username,String password) {
        User user=userMapper.selectUser(username,password);
        return user;
    }


    public boolean register(User user) throws CustomerException {
//        查询数据库中是否有这个用户
        try{
            User user1=userMapper.selectUserByUsername(user);
            if (user1 ==null ) {
                int res1=userMapper.save(new User(user.getUsername(),user.getPassword()));
                return res1 != 0;
            }else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new CustomerException(e.getMessage());
        }
    }

    public List<User> getAllUsers(String name,String phone,String email) throws CustomerException{
        return userMapper.getAllUsers(name,phone,email);
    }

    public void save(User user) {
        User user1 = userMapper.selectUserByUsername(user);
        if (user1 != null) {
            throw new CustomerException("用户名重复");
        }
        if (StrUtil.isBlank(user.getPassword())) {
            user.setPassword(PWD);
        }
        if (user.getAge() > 100||user.getAge() <0) {
            throw new CustomerException("请输入合法的年龄");
        }
        if (user.getPhone().length() < 11) {
            throw new CustomerException("手机号必须为11位");
        }
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String email = user.getEmail();
        if (!email.matches(regex)) {
            throw new CustomerException("邮箱格式不正确");
        }
        userMapper.insert(user);
    }
}
