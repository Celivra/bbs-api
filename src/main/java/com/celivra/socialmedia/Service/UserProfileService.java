package com.celivra.socialmedia.Service;

import com.celivra.socialmedia.Entity.UserProfile;
import com.celivra.socialmedia.Mapper.UserProfileMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    UserProfileMapper mapper;

    public UserProfile getProfile(int userId) {
        return mapper.findById(userId);
    }

    public boolean updateProfile(UserProfile profile) {
        return mapper.updateProfile(profile);
    }
    public boolean updateAvatar(Integer userId, String url){
        return mapper.updateAvatar(userId, url);
    }
}