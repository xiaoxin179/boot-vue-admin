package com.xiaoxin.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author:XIAOXIN
 * @date:2023/07/15
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String login() {
        return "SUCCESS";
    }

}
