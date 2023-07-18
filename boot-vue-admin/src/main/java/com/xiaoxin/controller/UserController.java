package com.xiaoxin.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xiaoxin.entity.User;
import com.xiaoxin.mapper.UserMapper;
import com.xiaoxin.utils.JDBCUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author:XIAOXIN
 * @date:2023/07/15
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String login(@RequestBody String userStr, HttpServletRequest request) throws SQLException {    //RquestBody可以把前台发送的json对象转换为java对象
        JSONObject parse = JSONUtil.parseObj(userStr);
//        前台的请求中获取两个的值,直接从json对象中获取属性
        String username = parse.getStr("username");
        String password = parse.getStr("password");
//        1.通过jdbc查询
//        User user = JDBCUtil.excuteQuery1(username, password);
//        2.通过mybatis来查询
        User user = userMapper.selectUser(username, password);
        String u_username = user.getUsername();
        String u_password = user.getPassword();
        if (user != null) {
//            如果不为空就直接把user存入session
            request.getSession().setAttribute("user",user);
            if (u_password.equals(password) && u_username.equals(username)) {
                return "SUCCESS";
            }
        }
        return "FALSE";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        response.sendRedirect("/login.html");
    }
}
