package com.celivra.bbs.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private String postId;
    private String parentId;
    private String content;
}
