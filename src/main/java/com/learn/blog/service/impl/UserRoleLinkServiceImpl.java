package com.learn.blog.service.impl;

import com.learn.blog.annotation.DataSourceSwitch;
import com.learn.blog.dao.RoleDao;
import com.learn.blog.dao.UserDao;
import com.learn.blog.dao.UserRoleLinkDao;
import com.learn.blog.enums.DataSourceNames;
import com.learn.blog.enums.ResponseCode;
import com.learn.blog.exception.SmartException;
import com.learn.blog.model.Role;
import com.learn.blog.model.User;
import com.learn.blog.model.UserRoleLink;
import com.learn.blog.service.UserRoleLinkService;
import com.learn.blog.utils.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleLinkServiceImpl implements UserRoleLinkService {
    @Autowired
    private UserRoleLinkDao userRoleLinkDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private SnowflakeIdUtils snowflakeIdUtils;

    @Override
    @DataSourceSwitch(name = DataSourceNames.POSTGRESQL)
    public void addUserRoleLink(UserRoleLink userRoleLink) {
        // 验证用户是否存在
        User user = userDao.queryUserById(userRoleLink.getUserId());
        if (user == null) {
            throw new SmartException(ResponseCode.USER_NOT_EXIST, new Object[]{userRoleLink.getUserId().toString()});
        }

        // 验证角色是否存在
        Role role = roleDao.queryRoleById(userRoleLink.getRoleId());
        if (role == null) {
            throw new SmartException(ResponseCode.RESOURCE_NOT_EXIST,
                    new Object[]{userRoleLink.getRoleId().toString()});
        }

        try {
            userRoleLink.setId(snowflakeIdUtils.nextId());
            userRoleLinkDao.insert(userRoleLink);
        } catch (Exception e) {
            throw new SmartException(ResponseCode.FAILURE, e);
        }

    }
}
