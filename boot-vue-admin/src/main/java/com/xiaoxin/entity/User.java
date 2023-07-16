package com.xiaoxin.entity;

import lombok.Data;

/**
 * @author:XIAOXIN
 * @date:2023/07/16
 **/
@Data
public class User {
    private int id;
    private String username;
    private String password;

    public User(int id, String username1, String password1) {
        this.username = username1;
        this.password = password1;

    }
}
