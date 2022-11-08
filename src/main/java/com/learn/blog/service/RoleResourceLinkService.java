package com.learn.blog.service;

import com.learn.blog.model.RoleResourceLink;

/**
 * 角色与资源关系service层
 */
public interface RoleResourceLinkService {
    /**
     * 添加角色与资源关系
     *
     * @param roleResourceLink roleResourceLink
     */
    void addRoleResourceLink(RoleResourceLink roleResourceLink);
}
