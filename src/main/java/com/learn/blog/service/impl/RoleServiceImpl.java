package com.learn.blog.service.impl;

import com.learn.blog.annotation.DataSourceSwitch;
import com.learn.blog.dao.RoleDao;
import com.learn.blog.enums.DataSourceNames;
import com.learn.blog.enums.ResponseCode;
import com.learn.blog.exception.SmartException;
import com.learn.blog.model.Role;
import com.learn.blog.service.RoleService;
import com.learn.blog.utils.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Resource
    private SnowflakeIdUtils snowflakeIdUtils;


    @Override
    @DataSourceSwitch(name = DataSourceNames.POSTGRESQL)
    public void addRole(Role role) {
        role.setId(snowflakeIdUtils.nextId());
        role.setCreateTime(new Date());
        try {
            roleDao.insert(role);
        } catch (Exception e) {
            throw new SmartException(ResponseCode.FAILURE, e);
        }
    }
}
