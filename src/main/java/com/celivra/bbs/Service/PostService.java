package com.celivra.bbs.Service;


import com.celivra.bbs.Dto.CreatePostDto;
import com.celivra.bbs.Entity.*;
import com.celivra.bbs.Mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {

    @Autowired
    PostMapper postMapper;
    @Autowired
    PostMediaMapper mediaMapper;
    @Autowired
    UserProfileMapper userProfileMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    // 发帖
    public int createPost(int userId, CreatePostDto dto) {
        String type = dto.getPtype();
        if(!(type.equals("文学") || type.equals("科技") || type.equals("生活") || type.equals("娱乐")) ){
            return 0;
        }

        Post post = new Post();
        post.setUserId(userId);
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setPtype(dto.getPtype());

        boolean ok = postMapper.createPost(post);
        if (!ok) return 0;

        int postId = post.getId();

        // 保存 media
        if (dto.getMediaList() != null) {
            for (CreatePostDto.MediaItem m : dto.getMediaList()) {
                PostMedia pm = new PostMedia();
                pm.setPostId(postId);
                pm.setMediaUrl(m.getUrl());
                pm.setMediaType(m.getType());
                mediaMapper.insert(pm);
            }
        }

        return postId;
    }
    public Post findById(int postId) {
        return postMapper.findById(postId);
    }

    public boolean updatePost(Post post, int userId) {

        Post oldPost = postMapper.findById(post.getId());
        // 帖子不存在
        if (oldPost == null) {
            return false;
        }
        // 不是本人
        if (oldPost.getUserId() != userId) {
            return false;
        }
        return postMapper.updatePost(post) > 0;
    }

    // 删帖（只能删自己的）
    public boolean deletePost(int postId, int userId) {
        Post post = postMapper.findById(postId);
        List<Comment> comments = commentMapper.findByPostId(postId);
        List<Notification>  notifications = notificationMapper.findByRelatedId(postId);
        for(Comment comment : comments){
            commentMapper.deleteById(comment.getId());
        }
        for(Notification notification : notifications){
            notificationMapper.delete(notification.getId());
        }

        if (post == null || post.getUserId() != userId) return false;

        mediaMapper.deleteByPostId(postId);
        commentMapper.deleteByPostId(postId);
        return postMapper.deletePost(postId, userId);
    }

    // 帖子详情
    public Map<String, Object> getPostDetail(int postId) {
        Post post = postMapper.findById(postId);
        List<PostMedia> medias = mediaMapper.findByPostId(postId);
        Map<String, Object> map = new HashMap<>();
        UserProfile author = userProfileMapper.findById(post.getUserId());
        map.put("post", post);
        map.put("media", medias);
        map.put("author", author);
        return map;
    }
    public List<Post> UserListPosts(int userId){
        List<Post> posts = postMapper.findAllByUserID(userId);
        return posts;
    }
    // ⭐ 1. 帖子列表
    public List<Map<String, Object>> listPosts() {
        List<Post> posts = postMapper.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Post p : posts) {
            List<PostMedia> medias = mediaMapper.findByPostId(p.getId());

            Map<String, Object> map = new HashMap<>();
            UserProfile author = userProfileMapper.findById(p.getUserId());
            map.put("author", author);
            map.put("post", p);
            map.put("media", medias);
            result.add(map);
        }

        return result;
    }

    // ⭐ 2. 某帖子所有媒体
    public List<PostMedia> getPostMedia(int postId) {
        return mediaMapper.findByPostId(postId);
    }

    public List<Map<String,Object>> searchPosts(String kw){
        List<Post> posts = postMapper.search(kw);
        List<Map<String,Object>> list = new ArrayList<>();

        for(Post p : posts){
            Map<String,Object> map = new HashMap<>();
            map.put("post", p);
            map.put("author", userProfileMapper.findById(p.getUserId()));
            map.put("media", mediaMapper.findByPostId(p.getId()));
            list.add(map);
        }
        return list;
    }

    public List<Map<String,Object>> searchPostsByType(String type){
        List<Post> posts = postMapper.searchByType(type);
        List<Map<String,Object>> list = new ArrayList<>();

        for(Post p : posts){
            Map<String,Object> map = new HashMap<>();
            map.put("post", p);
            map.put("author", userProfileMapper.findById(p.getUserId()));
            map.put("media", mediaMapper.findByPostId(p.getId()));
            list.add(map);
        }
        return list;
    }
}