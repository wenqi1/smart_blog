package com.learn.blog.dao;

import com.learn.blog.model.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserDao extends Mapper<User> {
    /**
     * 根据主键修改用户信息
     *
     * @param user user
     * @return 影响的行数
     */
    int updateUserInfo(User user);

    /**
     * 查询手机是否已注册
     *
     * @param phone 手机号
     * @return User
     */
    User queryUserByPhone(String phone);

    /**
     * 根据用户名查找用户
     *
     * @param name 用户名
     * @return User
     */
    User queryUserByUsername(String name);

}
