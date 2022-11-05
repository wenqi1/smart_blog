package com.learn.blog.service;

import com.learn.blog.model.User;

/**
 * 用户service层
 */
public interface UserService {
    /**
     * 用户登录
     *
     * @param phone 手机号
     * @param userPassword 密码
     * @return User
     */
    User login(String phone, String userPassword);

    /**
     * 注册
     *
     * @param user user
     */
    void register(User user);

    /**
     * 修改用户信息
     *
     * @param user user
     */
    void update(User user);

}
