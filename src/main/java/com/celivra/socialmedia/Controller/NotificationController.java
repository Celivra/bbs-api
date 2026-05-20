package com.celivra.socialmedia.Controller;

import com.celivra.socialmedia.Common.Result;
import com.celivra.socialmedia.Entity.User;
import com.celivra.socialmedia.Service.NotificationService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
