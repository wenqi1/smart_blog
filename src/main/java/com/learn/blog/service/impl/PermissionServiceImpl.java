package com.learn.blog.service.impl;

import com.learn.blog.annotation.DataSourceSwitch;
import com.learn.blog.dao.ResourceDao;
import com.learn.blog.dao.RoleResourceLinkDao;
import com.learn.blog.dao.UserRoleLinkDao;
import com.learn.blog.enums.DataSourceNames;
import com.learn.blog.enums.ResponseCode;
import com.learn.blog.exception.SmartException;
import com.learn.blog.model.Resource;
import com.learn.blog.model.RoleResourceLink;
import com.learn.blog.model.UserRoleLink;
import com.learn.blog.service.PermissionService;
import com.learn.blog.service.ResourceService;
import com.learn.blog.vo.ResourceTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private UserRoleLinkDao userRoleLinkDao;

    @Autowired
    private RoleResourceLinkDao roleResourceLinkDao;
    
    @Autowired
    private ResourceService resourceService;

    @Override
    @DataSourceSwitch(name = DataSourceNames.POSTGRESQL)
    public List<ResourceTreeVo> queryUserPermissionTree(Long userId) {
        return resourceService.recursionResource(queryResourceByUserId(userId), 0L);
    }

    @Override
    @DataSourceSwitch(name = DataSourceNames.POSTGRESQL)
    public List<Resource> queryUserPermission(Long userId) {
        return queryResourceByUserId(userId);
    }

    private List<Resource> queryResourceByUserId(Long userId) {
        try {
            // 查询出用户的所有角色关系
            List<UserRoleLink> userRoleLinks = userRoleLinkDao.queryUserRoleLinkByUserId(userId);
            List<Long> roleIds = userRoleLinks.stream().map(UserRoleLink::getRoleId).collect(Collectors.toList());
            // 查询出所有角色的所有资源关系
            List<RoleResourceLink> roleResourceLinks = roleResourceLinkDao.queryRoleResourceLinkByRoleIds(roleIds);
            List<Long> resourceIds = roleResourceLinks.stream().map(RoleResourceLink::getResourceId)
                    .collect(Collectors.toList());
            // 查询出用户的所有资源
            List<Resource> resources = resourceDao.queryResourceByIds(resourceIds);
            // 查询出所有资源的父资源
            List<Long> allResourceIds = new ArrayList<>();
            resources.forEach(resource -> {
                allResourceIds.add(resource.getId());
                allResourceIds.addAll(Arrays.stream(resource.getPath().split(",")).map(Long::parseLong)
                        .collect(Collectors.toList()));
            });
            return  resourceDao.queryResourceByIds(allResourceIds);
        } catch (Exception e) {
            throw new SmartException(ResponseCode.FAILURE, e);
        }
    }
}
