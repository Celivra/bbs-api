package com.celivra.socialmedia.Controller;

import com.celivra.socialmedia.Common.Result;
import com.celivra.socialmedia.Dto.PostIdDto;
import com.celivra.socialmedia.Entity.Comment;
import com.celivra.socialmedia.Entity.Post;
import com.celivra.socialmedia.Entity.User;
import com.celivra.socialmedia.Service.CommentService;
import com.celivra.socialmedia.Service.PostService;
import com.celivra.socialmedia.Service.UserService;

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

    // 发表评论
    @PostMapping("/add")
    public Result<?> add(@RequestBody Comment comment, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) return Result.fail("未登录");
        Post post = postService.findById(comment.getPostId());
        if (post == null) return Result.fail("帖子不存在");

        comment.setUserId(user.getId());
        comment.setSender(user.getUsername());
        boolean ok = commentService.add(comment, user.getId());
        return ok ? Result.success("评论成功") : Result.fail("评论失败");
    }

    // 获取评论
    @GetMapping("/list")
    public Result<?> list(@RequestBody PostIdDto postId){
        List<Comment> comments = commentService.getByPostId(postId.getPostId());
        return Result.success(comments);
    }

    @GetMapping("/tree/{postId}")
    public Result<?> tree(@PathVariable int postId){
        return Result.success(commentService.getCommentTree(postId));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable int id, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) return Result.fail("未登录");
        boolean ok = commentService.delete(id);
        return ok ? Result.success("删除成功") : Result.fail("删除失败");
    }
}
