package com.celivra.bbs.Service;

import com.celivra.bbs.Entity.Comment;
import com.celivra.bbs.Entity.Post;
import com.celivra.bbs.Entity.User;
import com.celivra.bbs.Mapper.CommentMapper;
import com.celivra.bbs.Mapper.NotificationMapper;

import com.celivra.bbs.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    NotificationMapper notificationMapper;
    @Autowired
    PostService postService;
    @Autowired
    private UserMapper userMapper;

    public boolean add(Comment comment, int senderId){
        comment.setUserId(senderId);
        // 评论帖子
        if (comment.getParentId() == null) {
            Post post = postService.findById(comment.getPostId());
            if (post != null && post.getUserId() != senderId) {
                notificationMapper.insert(
                        post.getUserId(),
                        senderId,
                        "COMMENT",
                        comment.getPostId(),
                        comment.getSender() + "评论了你的帖子"
                );
            }
        } else {
            System.out.println("parent Id:"+comment.getParentId());
            // 回复评论（可扩展：通知评论者）
            Post post = postService.findById(comment.getPostId());
            if (post != null && post.getUserId() != senderId) {
                notificationMapper.insert(
                        post.getUserId(),
                        senderId,
                        "REPLY",
                        comment.getPostId(),
                        comment.getSender() + "回复了你的评论"
                );
            }
            Comment parentComment = commentMapper.findById(comment.getParentId());
            User parentUser = userMapper.findById(parentComment.getUserId());
            String content = comment.getContent();
            comment.setContent("回复"+parentUser.getUsername()+":"+content);
        }

        return commentMapper.add(comment);
    }

    public List<Comment> getByPostId(String postId){
        return commentMapper.findByPostId(Integer.parseInt(postId) );
    }

    public boolean delete(int commentId){
        return commentMapper.deleteById(commentId);
    }
}