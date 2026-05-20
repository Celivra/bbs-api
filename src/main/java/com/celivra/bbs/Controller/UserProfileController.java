package com.celivra.bbs.Controller;


import com.celivra.bbs.Common.Result;
import com.celivra.bbs.Entity.User;
import com.celivra.bbs.Entity.UserProfile;
import com.celivra.bbs.Service.UserProfileService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    @Autowired
    UserProfileService profileService;

    // 获取自己的资料
    @GetMapping("/me")
    public Result<?> getMyProfile(HttpSession session) {
        User user = (User) session.getAttribute("user");
        UserProfile profile = profileService.getProfile(user.getId());
        return Result.success(profile);
    }

    // 获取他人资料
    @GetMapping("/{userId}")
    public Result<?> getUserProfile(@PathVariable int userId) {
        UserProfile profile = profileService.getProfile(userId);
        if (profile == null) return Result.fail("用户不存在");
        return Result.success(profile);
    }

    // 修改资料
    @PostMapping("/update")
    public Result<?> updateProfile(@RequestBody UserProfile profile, HttpSession session) {
        User user = (User) session.getAttribute("user");
        UserProfile profile1 = profileService.getProfile(user.getId());

        if(profile.getAvatarUrl() == null)profile.setAvatarUrl(profile1.getAvatarUrl());
        profile.setId(user.getId()); // 强制只更新自己的

        boolean ok = profileService.updateProfile(profile);
        return ok ? Result.success("更新成功") : Result.fail("更新失败");
    }
}