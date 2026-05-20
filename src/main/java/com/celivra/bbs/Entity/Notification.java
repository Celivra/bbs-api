package com.celivra.bbs.Entity;

import lombok.Data;

@Data
public class Notification {
    private Integer id;

    // 接收通知的用户
    private Integer userId;

    // 谁触发的通知
    private Integer fromUserId;

    // 通知类型：comment / reply
    private String type;

    // 关联的资源：帖子ID 或者 NULL（比如私信）
    private Integer relatedId;

    // 通知内容，例如 “Alice 赞了你的帖子”
    private String content;

    // 是否已读：0 未读 / 1 已读
    private Integer isRead;

    private String createTime;

    // -----------------------------
    //  以下是扩展字段，不存数据库
    // -----------------------------

    // 触发通知的用户头像
    private String fromAvatar;

    // 触发通知的用户名
    private String fromUsername;
}
