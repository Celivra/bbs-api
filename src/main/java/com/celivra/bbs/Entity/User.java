package com.celivra.bbs.Entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;
    private String email;
    private String phone;
    private String username;
    private String password;
    private LocalDateTime createTime;

    public User() { }

    public User(Integer id, String email, String phone, String username, String password,  LocalDateTime createTime) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.createTime = createTime;
    }

    public User(String email, String phone, String username, String password,  LocalDateTime createTime) {
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.createTime = createTime;
    }
}
