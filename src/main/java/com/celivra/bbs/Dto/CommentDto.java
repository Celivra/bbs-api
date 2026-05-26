package com.celivra.bbs.Dto;

import lombok.Data;

@Data
public class CommentDto {
    private String postId;
    private String parentId;
    private String content;
}
