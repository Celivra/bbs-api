package com.celivra.socialmedia.Service;

import com.celivra.socialmedia.Entity.Comment;
import com.celivra.socialmedia.Entity.Post;
import com.celivra.socialmedia.Mapper.CommentMapper;
import com.celivra.socialmedia.Mapper.NotificationMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    NotificationMapper nMapper;
    @Autowired
    PostService postService;

    public boolean add(Comment comment, int senderId){
        comment.setUserId(senderId);
        // 评论帖子
        if (comment.getParentId() == null) {
            Post post = postService.findById(comment.getPostId());
            if (post != null && post.getUserId() != senderId) {
                nMapper.insert(
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

    public List<Comment> getByPostId(Integer postId){
        return commentMapper.findByPostId(postId);
    }


    public boolean delete(int commentId){
        return commentMapper.deleteById(commentId);
    }

    public List<Comment> getCommentTree(Integer postId) {

        List<Comment> comments = commentMapper.findByPostId(postId);

        // key: commentId, value: comment
        Map<Integer, Comment> map = new HashMap<>();

        List<Comment> roots = new ArrayList<>();

        // 初始化 map
        for (Comment c : comments) {
            c.setReplies(new ArrayList<>());
            map.put(c.getId(), c);
        }
        // 构建树
        for (Comment c : comments) {

            if (c.getParentId() == null) {
                roots.add(c);
            } else {
                Comment parent = map.get(c.getParentId());
                if (parent != null) {
                    parent.getReplies().add(c);
                } else {
                    // 父评论不存在（异常数据）
                    roots.add(c);
                }
            }
        }

        return roots;
    }
}