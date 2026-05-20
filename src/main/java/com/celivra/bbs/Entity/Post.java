package com.celivra.bbs.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class Post {
    private Integer id;
    private Integer userId;
    private String title;
    private String ptype;
    private String content;
    private Date createTime;

    public Post() { }

    public Post(Integer userId, String title, String ptype, String content, Date createTime) {
        this.userId = userId;
        this.title = title;
        this.ptype = ptype;
        this.content = content;
        this.createTime = createTime;
    }

    public Post(Integer id, Integer userId, String title, String ptype, String content, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.ptype = ptype;
        this.content = content;
        this.createTime = createTime;
    }
}