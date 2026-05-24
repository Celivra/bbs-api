package com.celivra.bbs.Controller;

import com.celivra.bbs.Common.Result;
import com.celivra.bbs.Entity.Comment;
import com.celivra.bbs.Entity.Post;
import com.celivra.bbs.Entity.User;
import com.celivra.bbs.Entity.UserProfile;
import com.celivra.bbs.Service.CommentService;
import com.celivra.bbs.Service.PostService;

import com.celivra.bbs.Service.UserProfileService;
import com.celivra.bbs.Service.UserService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserProfileService userProfileService;

    // 发表评论
    @PostMapping("/add")
    public Result<?> add(@RequestBody Comment comment, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) return Result.fail("未登录");
        Post post = postService.findById(comment.getPostId());
        if (post == null) return Result.fail("帖子不存在");

        UserProfile profile = userProfileService.getProfile(user.getId());
        comment.setUserId(user.getId());
        comment.setSender(user.getUsername());
        comment.setAvatarUrl(profile.getAvatarUrl());
        boolean ok = commentService.add(comment, user.getId());
        return ok ? Result.success("评论成功") : Result.fail("评论失败");
    }

    // 获取评论
    @GetMapping("/list/{postId}")
    public Result<?> list(@PathVariable String postId){
        List<Comment> comments = commentService.getByPostId(postId);
        System.out.println("here is comment list for post"+postId);
        for(Comment comment : comments){
            System.out.println(comment);
        }
        return Result.success(comments);
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable int id, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) return Result.fail("未登录");
        boolean ok = commentService.delete(id);
        return ok ? Result.success("删除成功") : Result.fail("删除失败");
    }
}
