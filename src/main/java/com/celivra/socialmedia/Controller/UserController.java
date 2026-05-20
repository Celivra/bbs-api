package com.celivra.socialmedia.Controller;


import com.celivra.socialmedia.Common.Result;
import com.celivra.socialmedia.Dto.LoginUser;
import com.celivra.socialmedia.Entity.User;
import com.celivra.socialmedia.Service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    // 注册
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        int status = userService.register(user);
        if (status == 2) return Result.fail("用户名已存在");
        else if(status == 3) return Result.fail("邮箱已存在");
        else if(status == 4) return Result.fail("手机号已存在");

        return Result.success("注册成功");
    }

    // 登录
    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginUser loginUser, HttpSession session) {
        System.out.println(loginUser);
        User user = userService.login(loginUser.getFirst(),loginUser.getLast());

        if (user == null)return Result.fail("用户不存在或者密码错误");

        session.setAttribute("user", user);
        return Result.success(user);
    }
    @GetMapping("/logout")
    public Result<?> logout(HttpSession session) {

        session.removeAttribute("user");
        return Result.success("登出成功");
    }

}