package com.celivra.socialmedia.Dto;

import lombok.Data;

import java.util.List;

@Data
public class CreatePostDto {
    String title;
    String content;
    List<MediaItem> mediaList;

    @Data
    public static class MediaItem {
        String url;
        Integer type; // 1=图片，2=视频
    }
}