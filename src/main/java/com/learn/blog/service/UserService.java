package com.learn.blog.service;

import com.learn.blog.model.User;

public interface UserService {
    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 是否修改成功
     */
    boolean updateUserInfo(User user);

    /**
     * 通过用户id查询用户信息
     *
     * @param uId 用户id
     * @return User
     */
    User getUserInfoById(Integer uId);


    /**
     * 用户登录
     *
     * @param userName 用户名
     * @param userPassword 密码
     */
    void login(String userName, String userPassword);

    /**
     * 注册
     *
     * @param user user
     */
    void register(User user);
}
