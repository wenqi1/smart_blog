package com.learn.blog.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.learn.blog.annotation.DataSourceSwitch;
import com.learn.blog.dao.*;
import com.learn.blog.dto.ArticlePushDto;
import com.learn.blog.dto.ArticleSaveDto;
import com.learn.blog.enums.DataSourceNames;
import com.learn.blog.enums.ResponseCode;
import com.learn.blog.exception.SmartException;
import com.learn.blog.model.*;
import com.learn.blog.service.ArticleService;
import com.learn.blog.utils.SnowflakeIdUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleDetailDao articleDetailDao;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ArticleTagLinkDao articleTagLinkDao;

    @Autowired
    private ArticleCategoryLinkDao articleCategoryLinkDao;

    @Autowired
    private SnowflakeIdUtils snowflakeIdUtils;

    @Override
    @DataSourceSwitch(name = DataSourceNames.POSTGRESQL)
    @Transactional(transactionManager = "postgresqlTransactionManager")
    public void addArticle(ArticleSaveDto articleSaveDto) {
        // contentMd 和 contentHtml 不能同时为空
        if (StringUtils.isEmpty(articleSaveDto.getContentMd()) && StringUtils.isEmpty(articleSaveDto.getContentHtml())) {
            throw new SmartException(ResponseCode.ARTICLE_CONTENT_IS_EMPTY);
        }
        // 判断用户是否存在
        Long userId = articleSaveDto.getUserId();
        User user = userDao.queryUserById(userId);
        if (user == null) {
            throw new SmartException(ResponseCode.USER_NOT_EXIST, new Object[]{userId});
        }
        // 封装Article
        Article article = new Article();
        BeanUtils.copyProperties(articleSaveDto, article);
        article.setId(snowflakeIdUtils.nextId());
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());

        // 封装ArticleDetail
        ArticleDetail articleDetail = new ArticleDetail();
        BeanUtils.copyProperties(articleSaveDto, articleDetail);
        articleDetail.setId(snowflakeIdUtils.nextId());
        articleDetail.setArticleId(article.getId());

        try {
            articleDao.insert(article);
            articleDetailDao.insert(articleDetail);
        } catch (Exception e) {
            throw new SmartException(ResponseCode.FAILURE, e);
        }
    }

    @Override
    @DataSourceSwitch(name = DataSourceNames.POSTGRESQL)
    @Transactional(transactionManager = "postgresqlTransactionManager")
    public void pushArticle(ArticlePushDto articlePushDto) {
        // 判断文章是否存在
        Article article = articleDao.queryArticleById(articlePushDto.getArticleId());
        if (article == null) {
            throw new SmartException(ResponseCode.ARTICLE_NOT_EXIST, new Object[]{articlePushDto.getArticleId()});
        }
        // 判断标签是否存在
        Tag tag = tagDao.queryTagById(articlePushDto.getTagId());
        if (tag == null) {
            throw new SmartException(ResponseCode.TAG_NOT_EXIST, new Object[]{articlePushDto.getTagId()});
        }
        // 判断分类是否存在
        Category category = categoryDao.queryCategoryById(articlePushDto.getCategoryId());
        if (category == null || !Objects.equals(category.getUserId(), article.getUserId())) {
            throw new SmartException(ResponseCode.CATEGORY_NOT_EXIST, new Object[]{articlePushDto.getCategoryId()});
        }

        try {
            // 封装Article
            article.setPushTime(new Date());
            article.setUpdateTime(new Date());
            articleDao.updateArticleById(article);

            // 封装ArticleTagLink
            ArticleTagLink articleTagLink = new ArticleTagLink();
            articleTagLink.setId(snowflakeIdUtils.nextId());
            articleTagLink.setArticleId(articlePushDto.getArticleId());
            articleTagLink.setTagId(articlePushDto.getTagId());
            articleTagLinkDao.insert(articleTagLink);

            // 封装ArticleCategoryLink
            ArticleCategoryLink articleCategoryLink = new ArticleCategoryLink();
            articleCategoryLink.setId(snowflakeIdUtils.nextId());
            articleCategoryLink.setArticleId(articlePushDto.getArticleId());
            articleCategoryLink.setCategoryId(articlePushDto.getCategoryId());
            articleCategoryLinkDao.insert(articleCategoryLink);
        } catch (Exception e) {
            throw new SmartException(ResponseCode.FAILURE, e);
        }
    }
}
