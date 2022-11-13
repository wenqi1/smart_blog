package com.learn.blog.service.impl;

import com.learn.blog.annotation.DataSourceSwitch;
import com.learn.blog.dao.ArticleDetailDao;
import com.learn.blog.enums.DataSourceNames;
import com.learn.blog.enums.ResponseCode;
import com.learn.blog.exception.SmartException;
import com.learn.blog.model.ArticleDetail;
import com.learn.blog.service.ArticleDetailService;
import com.learn.blog.utils.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleDetailServiceImpl implements ArticleDetailService {
    @Autowired
    private ArticleDetailDao articleDetailDao;

    @Autowired
    private SnowflakeIdUtils snowflakeIdUtils;

    @Override
    @DataSourceSwitch(name = DataSourceNames.POSTGRESQL)
    public void addArticleDetail(ArticleDetail articleDetail) {
        articleDetail.setId(snowflakeIdUtils.nextId());
        try {
            articleDetailDao.insert(articleDetail);
        } catch (Exception e) {
            throw new SmartException(ResponseCode.FAILURE, e);
        }
    }
}
