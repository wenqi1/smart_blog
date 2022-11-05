package com.learn.blog.dao;

import com.learn.blog.model.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserDao extends Mapper<User> {
    /**
     * 查询手机是否已注册
     *
     * @param phone 手机号
     * @return User
     */
    User queryUserByPhone(String phone);
}
