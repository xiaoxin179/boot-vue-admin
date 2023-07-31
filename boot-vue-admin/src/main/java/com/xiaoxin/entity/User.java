package com.xiaoxin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author:XIAOXIN
 * @date:2023/07/16
 **/
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")  //2022-06-22
    private Date birth;
    private String phone;
    private String email;
    private String role;
    private BigDecimal account;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String username1, String password1) {
        this.username = username1;
        this.password = password1;
    }

    public User(Integer id, String username, String password, String name, Integer age, Date birth, String phone, String email, String role, BigDecimal account) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.account = account;
    }
}
