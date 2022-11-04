package com.learn.blog.service.impl;

import com.learn.blog.annotation.DataSourceSwitch;
import com.learn.blog.dao.UserDao;
import com.learn.blog.enums.DataSourceNames;
import com.learn.blog.enums.ResponseCode;
import com.learn.blog.exception.SmartException;
import com.learn.blog.model.User;
import com.learn.blog.service.UserService;
import com.learn.blog.utils.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Resource
    private SnowflakeIdUtils snowflakeIdUtils;

    @Override
    public boolean updateUserInfo(User user) {
        return false;
    }

    @Override
    public User getUserInfoById(Integer uId) {
        return null;
    }

    @Override
    public void login(String userName, String userPassword) {
        User user = userDao.queryUserByUsername(userName);

    }

    @Override
    @DataSourceSwitch(name = DataSourceNames.POSTGRESQL)
    public void register(User user) {
        // 判断手机号是否已被注册
        User existUser = userDao.queryUserByPhone(user.getPhone());
        if (existUser != null) {
            throw new SmartException(ResponseCode.PHONE_EXIST);
        }
        user.setId(snowflakeIdUtils.nextId());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        try {
            userDao.insert(user);
        } catch (Exception e) {
            throw new SmartException(ResponseCode.FAILURE, e.getMessage());
        }

    }
}
