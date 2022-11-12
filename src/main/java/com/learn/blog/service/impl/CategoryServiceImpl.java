package com.learn.blog.service.impl;

import com.learn.blog.annotation.DataSourceSwitch;
import com.learn.blog.dao.CategoryDao;
import com.learn.blog.dao.UserDao;
import com.learn.blog.enums.DataSourceNames;
import com.learn.blog.enums.ResponseCode;
import com.learn.blog.exception.SmartException;
import com.learn.blog.model.Category;
import com.learn.blog.model.User;
import com.learn.blog.service.CategoryService;
import com.learn.blog.utils.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private SnowflakeIdUtils snowflakeIdUtils;

    @Override
    @DataSourceSwitch(name = DataSourceNames.POSTGRESQL)
    public void addCategory(Category category) {
        // 判断用户是否存在
        Long userId = category.getUserId();
        User user = userDao.queryUserById(userId);
        if (user == null) {
            throw new SmartException(ResponseCode.USER_NOT_EXIST, new Object[]{userId});
        }

        // 判断父分类存不存在
        Long parentId = category.getParentId();
        if (parentId != null) {
            Category parentCategory = categoryDao.queryCategoryById(parentId);
            if (parentCategory == null) {
                throw new SmartException(ResponseCode.CATEGORY_NOT_EXIST, new Object[]{parentId});
            }
        }

        category.setId(snowflakeIdUtils.nextId());
        category.setCreateTime(new Date());
        try {
            categoryDao.insert(category);
        } catch (Exception e) {
            throw new SmartException(ResponseCode.FAILURE, e);
        }

    }
}
