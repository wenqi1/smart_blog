package com.learn.blog.controller;

import com.learn.blog.enums.ResponseCode;
import com.learn.blog.model.APIResponse;
import com.learn.blog.service.PermissionService;
import com.learn.blog.vo.ResourceTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限控制controller层
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询用户的权限
     *
     * @param userId 用户id
     * @return APIResponse<List<ResourceTreeVo>>
     */
    @RequestMapping("/query")
    public APIResponse<List<ResourceTreeVo>> queryUserPermission(Long userId) {
        return new APIResponse<>(ResponseCode.SUCCESS, permissionService.queryUserPermission(userId));
    }
}
