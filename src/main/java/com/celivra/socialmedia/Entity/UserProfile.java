package com.celivra.socialmedia.Entity;

import lombok.Data;

@Data
public class UserProfile {
    private Integer id;        // 与 user.id 一致
    private String nickname;
    private String avatarUrl;
    private String bio;
    private Integer sex;         // 0=不想显示，1=男，2=女
    private String birthday;

    public UserProfile() { }

    public UserProfile(Integer id, String nickname, String avatarUrl, String bio, Integer sex, String birthday) {
        this.id = id;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
        this.bio = bio;
        this.sex = sex;
        this.birthday = birthday;
    }

    public UserProfile(String nickname, String avatarUrl, String bio, Integer sex, String birthday) {
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
        this.bio = bio;
        this.sex = sex;
        this.birthday = birthday;
    }
}