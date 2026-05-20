package com.celivra.socialmedia.Mapper;

import com.celivra.socialmedia.Entity.PostMedia;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMediaMapper {

    @Insert("INSERT INTO post_media(post_id, media_url, media_type) VALUES(#{postId}, #{mediaUrl}, #{mediaType})")
    void insert(PostMedia media);

    @Select("SELECT * FROM post_media WHERE post_id = #{postId}")
    List<PostMedia> findByPostId(int postId);

    @Delete("DELETE FROM post_media WHERE post_id = #{postId}")
    void deleteByPostId(int postId);
}