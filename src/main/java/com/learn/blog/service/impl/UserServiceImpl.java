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
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Resource
    private SnowflakeIdUtils snowflakeIdUtils;

    @Override
    @DataSourceSwitch(name = DataSourceNames.POSTGRESQL)
    public User login(String phone, String userPassword) {
        User user = null;
        try {
            user = userDao.queryUserByPhone(phone);
        } catch (Exception e) {
            throw new SmartException(ResponseCode.FAILURE, e);
        }

        if (user != null && user.getPassword().equals(userPassword)) {
            return user;
        }
        return null;

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
            throw new SmartException(ResponseCode.FAILURE, e);
        }

    }

    @Override
    @DataSourceSwitch(name = DataSourceNames.POSTGRESQL)
    public void update(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phone", user.getPhone());
        user.setUpdateTime(new Date());
        int result;
        try {
             result = userDao.updateByExampleSelective(user, example);
        } catch (Exception e) {
            throw new SmartException(ResponseCode.FAILURE, e);
        }

        Object[] arg = new Object[]{user.getPhone()};
        if (result != 1) {
            throw new SmartException(ResponseCode.USER_NOT_EXIST, arg);
        }
    }
}
