package com.learn.blog.service;

import com.learn.blog.vo.ResourceTreeVo;

import java.util.List;

/**
 * 权限控制service层
 */
public interface PermissionService {
    /**
     * 查询用户权限
     *
     * @param userId 用户id
     * @Return List<ResourceTreeVo>
     */
    List<ResourceTreeVo> queryUserPermission(Long userId);
}
