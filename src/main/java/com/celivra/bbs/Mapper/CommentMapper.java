package com.celivra.bbs.Mapper;

import com.celivra.bbs.Entity.Comment;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("""
        INSERT INTO comments(post_id, user_id, avatar_url, parent_id, sender, content)
        VALUES(#{postId}, #{userId}, #{avatarUrl},#{parentId}, #{sender}, #{content})
        """)
    boolean add(Comment comment);

    @Delete("DELETE FROM comments WHERE id = #{id}")
    boolean deleteById(int id);

    @Delete("DELETE FROM comments WHERE post_id = #{postId}")
    boolean deleteByPostId(int postId);

    @Select("SELECT * from comments where id = #{id}")
    Comment findById(int id);

    @Select("""
        SELECT * FROM comments 
        WHERE post_id = #{postId}
        ORDER BY create_time ASC
        """)
    List<Comment> findByPostId(Integer postId);
}