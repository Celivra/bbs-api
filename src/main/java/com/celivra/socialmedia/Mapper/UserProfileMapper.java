package com.celivra.socialmedia.Mapper;

import com.celivra.socialmedia.Entity.UserProfile;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserProfileMapper {

    @Insert("INSERT INTO user_profile(id, nickname) VALUES(#{id}, #{nickname})")
    int createProfile(Integer id);

    @Select("SELECT * FROM user_profile WHERE id = #{id}")
    UserProfile findById(Integer id);

    @Update("UPDATE user_profile SET nickname=#{nickname}, avatar_url=#{avatarUrl}, " +
            "bio=#{bio}, sex=#{sex}, birthday=#{birthday} WHERE id=#{id}")
    boolean updateProfile(UserProfile profile);
    @Update("UPDATE user_profile SET avatar_url = #{url} WHERE id = #{userId}")
    boolean updateAvatar(Integer userId, String url);
}
