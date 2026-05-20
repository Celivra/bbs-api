package com.celivra.socialmedia.Entity;

import lombok.Data;

@Data
public class PostMedia {
    private Integer id;
    private Integer postId;
    private String mediaUrl;
    private Integer mediaType; // 1=图片，2=视频

    public PostMedia() {}

    public PostMedia(Integer id, Integer postId, String mediaUrl, Integer mediaType) {
        this.id = id;
        this.postId = postId;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
    }

    public PostMedia(Integer postId, String mediaUrl, Integer mediaType) {
        this.postId = postId;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
    }
}
