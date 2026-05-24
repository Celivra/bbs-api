package com.celivra.bbs.Entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Comment {
    private Integer id;
    private Integer postId;
    private Integer userId;
    private String avatarUrl;
    private Integer parentId;
    private String sender;
    private String content;
    private Date createTime;

    private List<Comment> replies;

    public Comment(){}

    public Comment(Integer id, Integer postId, Integer userId, String avatarUrl ,Integer parentId, String sender, String content, Date createTime) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.avatarUrl = avatarUrl;
        this.parentId = parentId;
        this.sender = sender;
        this.content = content;
        this.createTime = createTime;
    }

    public Comment(Integer postId, Integer userId, String avatarUrl, Integer parentId, String sender, String content, Date createTime) {
        this.postId = postId;
        this.userId = userId;
        this.avatarUrl = avatarUrl;
        this.parentId = parentId;
        this.sender = sender;
        this.content = content;
        this.createTime = createTime;
    }
}
