package com.celivra.bbs.Service;


import com.celivra.bbs.Entity.User;
import com.celivra.bbs.Mapper.UserMapper;
import com.celivra.bbs.Mapper.UserProfileMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserProfileMapper userProfileMapper;

    public User findUserById(int id){
        return userMapper.findById(id);
    }

    //1=success, 2=same username, 3=same email, 4=same phone, 0=server error
    public int register(User user){
        User temp = userMapper.findByUsername(user.getUsername());
        if(temp != null)return 2;
        temp =  userMapper.findByEmail(user.getEmail());
        if(temp != null) return 3;
        temp =  userMapper.findByPhone(user.getPhone());
        if(temp != null) return 4;


        boolean ok = userMapper.register(user)>0;
        User temp2 = userMapper.findByUsername(user.getUsername());
        if(ok){
            userProfileMapper.createProfile(temp2.getId());
            return 1;
        }
        return 0;
    }

    //1=success, 2=user not exist, 0=password error
    public User login(String first, String password){

        User user = userMapper.findByUsername(first);
        if(user == null){
            user =  userMapper.findByEmail(first);
        }
        if(user == null){
            user =  userMapper.findByPhone(first);
        }
        if(user == null) return null;

        if(user.getPassword().equals(password)) return user;
        else return null;
    }
}