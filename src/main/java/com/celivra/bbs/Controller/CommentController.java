package com.celivra.bbs.Controller;

import com.celivra.bbs.Common.Result;
import com.celivra.bbs.Dto.CommentDto;
import com.celivra.bbs.Entity.Comment;
import com.celivra.bbs.Entity.Post;
import com.celivra.bbs.Entity.User;
import com.celivra.bbs.Entity.UserProfile;
import com.celivra.bbs.Service.CommentService;
import com.celivra.bbs.Service.PostService;

import com.celivra.bbs.Service.UserProfileService;
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
    public Result<?> add(@RequestBody CommentDto commentDto, HttpSession session){
        User user = (User) session.getAttribute("user");
        UserProfile userProfile = userProfileService.getProfile(user.getId());
        Post post = postService.findById(Integer.parseInt(commentDto.getPostId()));
        if (post == null) return Result.fail("帖子不存在");

        Comment comment = new Comment();
        comment.setUserId(user.getId());
        comment.setSender(userProfile.getNickname());
        comment.setPostId(post.getId());
        comment.setContent(commentDto.getContent());
        if(commentDto.getParentId() == null){
            comment.setParentId(null);
        }else{
            comment.setParentId(Integer.parseInt(commentDto.getParentId()));
        }
        boolean ok = commentService.add(comment, user.getId());
        return ok ? Result.success("评论成功") : Result.fail("评论失败");
    }

    // 获取评论
    @GetMapping("/list/{postId}")
    public Result<?> list(@PathVariable String postId){
        List<Comment> comments = commentService.getByPostId(postId);
        return Result.success(comments);
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable int id){
        boolean ok = commentService.delete(id);
        return ok ? Result.success("删除成功") : Result.fail("删除失败");
    }
}
