package com.learn.blog.service;

import com.learn.blog.model.Resource;
import com.learn.blog.vo.ResourceTreeVo;

import java.util.List;

/**
 * 权限控制service层
 */
public interface PermissionService {
    /**
     * 查询用户权限树
     *
     * @param userId 用户id
     * @Return List<ResourceTreeVo>
     */
    List<ResourceTreeVo> queryUserPermissionTree(Long userId);

    /**
     * 查询用户权限
     *
     * @param userId 用户id
     * @Return List<Resource>
     */
    List<Resource> queryUserPermission(Long userId);
}
