package com.celivra.bbs.Entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String email;
    private String phone;
    private String username;
    private String password;

    public User() { }

    public User(Integer id, String email, String phone, String username, String password) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }

    public User(String email, String phone, String username, String password) {
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }
}
