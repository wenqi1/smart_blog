package com.learn.blog.service.impl;

import com.learn.blog.dao.UserDao;
import com.learn.blog.model.User;
import com.learn.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean updateUserInfo(User user) {
        return false;
    }

    @Override
    public User getUserInfoById(Integer uId) {
        return null;
    }

    @Override
    public User login(String userName, String userPassword) {
        return userDao.getUserInfoByUsername(userName, userPassword);
    }

    @Override
    public void register(User user) {
        userDao.insertUserInfo(user);
    }
}
