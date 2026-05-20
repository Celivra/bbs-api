package com.celivra.socialmedia.Service;
import com.celivra.socialmedia.Entity.Notification;
import com.celivra.socialmedia.Mapper.NotificationMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationMapper notificationMapper;

    public List<Notification> list(Integer userId) {
        return notificationMapper.list(userId);
    }

    public Integer unreadCount(Integer userId) {
        return notificationMapper.unreadCount(userId);
    }

    public void markAllRead(Integer userId) {
        notificationMapper.markAllRead(userId);
    }
}
