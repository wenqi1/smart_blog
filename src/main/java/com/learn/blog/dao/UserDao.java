package com.learn.blog.dao;

import com.learn.blog.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    int updateUserInfo(User user);

    User getUserInfoById(Integer userId);

    User getUserInfoByUsername(String userName, String userPassword);

    void insertUserInfo(User user);
}
