package com.celivra.bbs.Controller;

import com.celivra.bbs.Common.Result;
import com.celivra.bbs.Entity.User;
import com.celivra.bbs.Service.NotificationService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/list")
    public Result<?> list(HttpSession session) {
        User loginUser = (User) session.getAttribute("user");
        if (loginUser == null) return Result.fail("未登录");

        return Result.success(notificationService.list(loginUser.getId()));
    }

    @GetMapping("/unread")
    public Result<?> unread(HttpSession session) {
        User loginUser = (User) session.getAttribute("user");
        if (loginUser == null) return Result.fail("未登录");

        return Result.success(notificationService.unreadCount(loginUser.getId()));
    }

    @PostMapping("/readAll")
    public Result<?> readAll(HttpSession session) {
        User loginUser = (User) session.getAttribute("user");
        if (loginUser == null) return Result.fail("未登录");

        notificationService.markAllRead(loginUser.getId());
        return Result.success("已读");
    }
    @PostMapping("/read/{id}")
    public Result<?> readOne(@PathVariable int id) {
        notificationService.markOneRead(id);
        return Result.success("已读");
    }
}
