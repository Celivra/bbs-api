package com.celivra.bbs.Service;

import com.celivra.bbs.Entity.Comment;
import com.celivra.bbs.Entity.Post;
import com.celivra.bbs.Entity.User;
import com.celivra.bbs.Entity.UserProfile;
import com.celivra.bbs.Mapper.*;

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
    PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserProfileMapper userProfileMapper;

    public boolean add(Comment comment, int senderId){
        comment.setUserId(senderId);
        // 评论帖子
        if (comment.getParentId() == null) {
            Post post = postMapper.findById(comment.getPostId());
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
            // 回复评论（可扩展：通知评论者）
            Comment parentComment = commentMapper.findById(comment.getParentId());
            if(parentComment.getUserId() == senderId){
                return false;
            }
            notificationMapper.insert(
                    parentComment.getUserId(),
                    senderId,
                    "REPLY",
                    comment.getPostId(),
                    comment.getSender() + "回复了你的评论"
            );
            User parentUser = userMapper.findById(parentComment.getUserId());
            String content = comment.getContent();
            comment.setContent("回复"+parentUser.getUsername()+":"+content);
        }

        return commentMapper.add(comment);
    }

    public List<Comment> getByPostId(String postId){
        List<Comment> comments = commentMapper.findByPostId(Integer.parseInt(postId) );
        for (Comment comment : comments) {
            UserProfile profile = userProfileMapper.findById(comment.getUserId());
            comment.setAvatarUrl(profile.getAvatarUrl());
        }
        return comments;
    }

    public boolean delete(int commentId){
        return commentMapper.deleteById(commentId);
    }
}