package com.learn.blog.service.impl;

import com.learn.blog.annotation.DataSourceSwitch;
import com.learn.blog.dao.TagDao;
import com.learn.blog.enums.DataSourceNames;
import com.learn.blog.enums.ResponseCode;
import com.learn.blog.exception.SmartException;
import com.learn.blog.model.Tag;
import com.learn.blog.service.TagService;
import com.learn.blog.utils.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Autowired
    private SnowflakeIdUtils snowflakeIdUtils;

    @Override
    @DataSourceSwitch(name = DataSourceNames.POSTGRESQL)
    public void addTag(Tag tag) {
        tag.setId(snowflakeIdUtils.nextId());
        tag.setCreateTime(new Date());
        try {
            tagDao.insert(tag);
        } catch (Exception e) {
            throw new SmartException(ResponseCode.FAILURE, e);
        }
    }
}
