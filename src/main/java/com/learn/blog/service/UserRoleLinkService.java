package com.learn.blog.service;

import com.learn.blog.model.UserRoleLink;

/**
 * 用户与角色关系service层
 */
public interface UserRoleLinkService {
    /**
     * 添加用户与角色关系
     *
     * @param userRoleLink userRoleLink
     */
    void addUserRoleLink(UserRoleLink userRoleLink);
}
