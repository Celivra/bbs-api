package com.celivra.bbs.Controller;

import com.celivra.bbs.Common.Result;
import com.celivra.bbs.Entity.FileProperties;
import com.celivra.bbs.Entity.User;
import com.celivra.bbs.Service.UserProfileService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/upload")
public class UploadController {
    @Autowired
    FileProperties fileProperties;
    @Autowired
    UserProfileService userProfileService;

    @PostMapping("/media")
    public Result<?> upload(@RequestParam("file") MultipartFile file) throws Exception {

        String original = file.getOriginalFilename();
        String ext = original.substring(original.lastIndexOf("."));
        String filename = System.currentTimeMillis() + ext;

        File dest = new File(fileProperties.getUploadDir()+ "/media/" + filename);
        dest.getParentFile().mkdirs(); // 创建目录

        file.transferTo(dest);

        // 返回前端可访问的 URL
        return Result.success("/upload/media/" + filename);
    }
    // 上传用户头像

    @PostMapping("/avatar")
    public Result<?> uploadAvatar(@RequestParam("file") MultipartFile file,
                                  HttpSession session) throws Exception {

        User user = (User) session.getAttribute("user");

        String original = file.getOriginalFilename();
        String ext = original.substring(original.lastIndexOf("."));
        String filename = "avatar_" + user.getId() + "_" + System.currentTimeMillis() + ext;

        // 使用配置文件里的目录
        File dest = new File(fileProperties.getUploadDir() + "/avatar/" + filename);
        dest.getParentFile().mkdirs();

        file.transferTo(dest);

        String url = "/upload/avatar/" + filename;
        userProfileService.updateAvatar(user.getId(), url);

        return Result.success(url);
    }
}