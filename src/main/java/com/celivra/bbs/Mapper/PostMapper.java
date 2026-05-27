package com.celivra.bbs.Mapper;

import com.celivra.bbs.Entity.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {

    @Insert("INSERT INTO post(user_id, title, ptype, content) VALUES(#{userId}, #{title}, #{ptype}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean createPost(Post post);

    @Update("""
        update post
        set title = #{title},
            ptype = #{ptype},
            content = #{content}
        where id = #{id}
        """)
    int updatePost(Post post);

    @Delete("DELETE FROM post WHERE id = #{postId} AND user_id = #{userId}")
    boolean deletePost(int postId, int userId);

    @Select("SELECT * FROM post WHERE id = #{postId}")
    Post findById(int postId);
    // ⭐ 新增：帖子列表
    @Select("SELECT * FROM post ORDER BY create_time DESC")
    List<Post> findAll();

    @Select("SELECT * FROM post where user_id=#{id} ORDER BY create_time DESC")
    List<Post> findAllByUserID(int id);
    @Select("SELECT * FROM post WHERE title LIKE CONCAT('%',#{kw},'%') " +
            "OR content LIKE CONCAT('%',#{kw},'%') ORDER BY create_time DESC")
    List<Post> search(@Param("kw") String kw);
}
