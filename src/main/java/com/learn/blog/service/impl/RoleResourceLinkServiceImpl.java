package com.learn.blog.service.impl;

import com.learn.blog.annotation.DataSourceSwitch;
import com.learn.blog.dao.ResourceDao;
import com.learn.blog.dao.RoleDao;
import com.learn.blog.dao.RoleResourceLinkDao;
import com.learn.blog.enums.DataSourceNames;
import com.learn.blog.enums.ResponseCode;
import com.learn.blog.exception.SmartException;
import com.learn.blog.model.Resource;
import com.learn.blog.model.Role;
import com.learn.blog.model.RoleResourceLink;
import com.learn.blog.service.RoleResourceLinkService;
import com.learn.blog.utils.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleResourceLinkServiceImpl implements RoleResourceLinkService {
    @Autowired
    private RoleResourceLinkDao roleResourceLinkDao;

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private SnowflakeIdUtils snowflakeIdUtils;

    @Override
    @DataSourceSwitch(name = DataSourceNames.POSTGRESQL)
    public void addRoleResourceLink(RoleResourceLink roleResourceLink) {
        // 验证角色是否存在
        Role role = roleDao.queryRoleById(roleResourceLink.getRoleId());
        if (role == null) {
            throw new SmartException(ResponseCode.ROLE_NOT_EXIST,
                    new Object[]{roleResourceLink.getRoleId().toString()});
        }

        // 验证资源是否存在
        Resource resource = resourceDao.queryResourceById(roleResourceLink.getResourceId());
        if (resource == null) {
            throw new SmartException(ResponseCode.RESOURCE_NOT_EXIST,
                    new Object[]{roleResourceLink.getResourceId().toString()});
        }

        try {
            roleResourceLink.setId(snowflakeIdUtils.nextId());
            roleResourceLinkDao.insert(roleResourceLink);
        } catch (Exception e) {
            throw new SmartException(ResponseCode.FAILURE, e);
        }
    }
}
