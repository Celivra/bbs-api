package com.celivra.bbs.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private Integer senderId;
    private Integer postId;
    private Integer userId;
    private String sender;
    private String content;
    private Date createTime;
}
