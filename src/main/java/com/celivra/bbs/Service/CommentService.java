package com.celivra.bbs.Service;

import com.celivra.bbs.Entity.Comment;
import com.celivra.bbs.Entity.Post;
import com.celivra.bbs.Mapper.CommentMapper;
import com.celivra.bbs.Mapper.NotificationMapper;

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
            // 回复评论（可扩展：通知评论者）
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