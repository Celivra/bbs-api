package com.celivra.bbs.Mapper;

import com.celivra.bbs.Entity.Notification;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NotificationMapper {

    @Insert("""
        INSERT INTO notification(user_id, from_user_id, type, related_id, content)
        VALUES(#{userId}, #{fromUserId}, #{type}, #{relatedId}, #{content})
    """)
    void insert(Integer userId, Integer fromUserId, String type, Integer relatedId, String content);

    @Select("""
        SELECT n.*, u.nickname AS fromUsername, u.avatar_url AS fromAvatar
        FROM notification n
        JOIN user_profile u ON u.id = n.from_user_id
        WHERE n.user_id = #{userId}
        ORDER BY n.create_time DESC
    """)
    List<Notification> list(Integer userId);

    @Select("SELECT COUNT(*) FROM notification WHERE user_id = #{userId} AND is_read = 0")
    Integer unreadCount(Integer userId);

    @Update("UPDATE notification SET is_read = 1 WHERE user_id = #{userId}")
    void markAllRead(Integer userId);

    @Update("UPDATE notification SET is_read = 1 WHERE id = #{id}")
    void markOneRead(Integer id);
}
