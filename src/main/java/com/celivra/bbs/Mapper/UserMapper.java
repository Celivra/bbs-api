package com.celivra.bbs.Mapper;

import com.celivra.bbs.Entity.User;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(email, phone, username, password) " +
            "VALUES(#{email}, #{phone}, #{username}, #{password})")
    int register(User user);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Select("SELECT * FROM user WHERE email = #{email}")
    User findByEmail(String email);

    @Select("SELECT * FROM user WHERE phone = #{phone}")
    User findByPhone(String phone);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Integer id);

    @Update(" update user set password = #{newPass} where id = #{id} ")
    int updatePassword(@Param("id") Integer id, @Param("newPass") String newPass);
}