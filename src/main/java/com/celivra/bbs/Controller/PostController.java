package com.celivra.bbs.Controller;

import com.celivra.bbs.Common.Result;
import com.celivra.bbs.Dto.CreatePostDto;
import com.celivra.bbs.Entity.Post;
import com.celivra.bbs.Entity.User;
import com.celivra.bbs.Service.PostService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;

    // 发帖
    @PostMapping("/create")
    public Result<?> createPost(@RequestBody CreatePostDto dto, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int postId = postService.createPost(user.getId(), dto);

        return postId == 0 ? Result.fail("发帖失败") : Result.success(postId);
    }
    @PutMapping("/update")
    public Result<?> update(@RequestBody Post post, HttpSession session) {

        User user = (User) session.getAttribute("user");
        boolean ok = postService.updatePost(post, user.getId());

        return ok ? Result.success("修改成功") : Result.fail("修改失败或无权限");
    }

    // 删除帖子
    @DeleteMapping("/{postId}")
    public Result<?> delete(@PathVariable int postId, HttpSession session) {
        User user = (User) session.getAttribute("user");

        boolean ok = postService.deletePost(postId, user.getId());
        return ok ? Result.success("删除成功") : Result.fail("无权删除");
    }

    // 帖子详情
    @GetMapping("/{postId}")
    public Result<?> detail(@PathVariable int postId) {
        return Result.success(postService.getPostDetail(postId));
    }

    //根据userid获取list
    @GetMapping("/list/{id}")
    public Result<?> Userlist(@PathVariable int id) {
        return Result.success(postService.UserListPosts(id));
    }
    // ⭐ 1. 帖子列表接口
    @GetMapping("/list")
    public Result<?> list() {
        return Result.success(postService.listPosts());
    }

    // ⭐ 2. 根据 postId 获取相关媒体
    @GetMapping("/{postId}/media")
    public Result<?> listMedia(@PathVariable int postId) {
        return Result.success(postService.getPostMedia(postId));
    }
    @GetMapping("/search")
    public Result<?> search(@RequestParam String kw){
        System.out.println(kw);
        return Result.success(postService.searchPosts(kw));
    }
}
