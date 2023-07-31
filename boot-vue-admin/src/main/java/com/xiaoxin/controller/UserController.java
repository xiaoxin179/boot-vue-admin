package com.xiaoxin.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xiaoxin.common.Result;
import com.xiaoxin.entity.User;
import com.xiaoxin.mapper.UserMapper;
import com.xiaoxin.service.UserService;
import com.xiaoxin.utils.JDBCUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author:XIAOXIN
 * @date:2023/07/15
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //    登录
    @RequestMapping(method = RequestMethod.POST, value = "/login")
//    在这里springMVC可以把前台传递过来的数据转换成java对象,所以一般情况下在controller层的代码中很多时候不直接写对象
    public Result<User> login(@RequestBody String userStr, HttpServletRequest request) throws SQLException {    //RquestBody可以把前台发送的json对象转换为java对象
        JSONObject parse = JSONUtil.parseObj(userStr);
//        前台的请求中获取两个的值,直接从json对象中获取属性
        String username = parse.getStr("username");
        String password = parse.getStr("password");
//        1.通过jdbc查询
//        User user = JDBCUtil.excuteQuery1(username, password);
//        2.通过mybatis来查询
        User user = userService.login(username, password);
        System.out.println(user.toString());
        String u_username = user.getUsername();
        String u_password = user.getPassword();
        if (user != null) {
//            如果不为空就直接把user存入session
            request.getSession().setAttribute("user", user);
            if (u_password.equals(password) && u_username.equals(username)) {
                return Result.success(user);
            }
        }
        return Result.error("账号或者密码错误");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        response.sendRedirect("/login.html");
    }

    //    注册
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public Result<Void> register(@RequestBody String userStr, HttpServletRequest request) {    //RquestBody可以把前台发送的json对象转换为java对象
        JSONObject parse = JSONUtil.parseObj(userStr);
//        前台的请求中获取两个的值,直接从json对象中获取属性
        String username = parse.getStr("username");
        String password = parse.getStr("password");
        User user = new User(username, password);
        Boolean res = userService.register(user);
        if (res != false) {
            return Result.success();
        } else {
            return Result.error("注册失败");
        }
    }
    @GetMapping("/all")
//    设置为非必须参数
    public Result<List<User>> getAllUsers( @RequestParam(required = false)String name, @RequestParam(required = false) String phone,@RequestParam(required = false)String email) {
        return Result.success(userService.getAllUsers(name,phone,email));
    }

    @PostMapping
    public Result<Void> add(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }
}

