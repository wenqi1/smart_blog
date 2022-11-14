package com.learn.blog.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.learn.blog.annotation.DataSourceSwitch;
import com.learn.blog.constant.ProjectConstant;
import com.learn.blog.dao.ArticleDao;
import com.learn.blog.dao.CommentDao;
import com.learn.blog.enums.DataSourceNames;
import com.learn.blog.enums.ResponseCode;
import com.learn.blog.exception.SmartException;
import com.learn.blog.model.Article;
import com.learn.blog.model.Comment;
import com.learn.blog.service.CommentService;
import com.learn.blog.utils.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class CommentServiceImpl implements CommentService {
    private static final String VISITOR_USER = "游客";

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private SnowflakeIdUtils snowflakeIdUtils;

    @Override
    @DataSourceSwitch(name = DataSourceNames.POSTGRESQL)
    public void addComment(Comment comment) {
        // 判断文章是否存在
        Long articleId = comment.getArticleId();
        Article article = articleDao.queryArticleById(articleId);
        if (article == null) {
            throw new SmartException(ResponseCode.ARTICLE_NOT_EXIST, new Object[]{articleId});
        }

        // 如果评论人为空，则赋值为游客
        if (StringUtils.isEmpty(comment.getCommentUserName())) {
            comment.setCommentUserName(VISITOR_USER + ProjectConstant.UNDERLINE + System.currentTimeMillis() +
                    ProjectConstant.UNDERLINE + new Random().nextInt(1000));
        }

        // 如果被评论人为空，但父id不为0 ，则为异常情况
        if (comment.getReplyUserName() == null && comment.getParentId() != 0) {
            throw new SmartException(ResponseCode.PARAM_EXCEPTION, new Object[]{"parentId" + "replyUserId"});
        }

        comment.setId(snowflakeIdUtils.nextId());
        comment.setCreateTime(new Date());
        try {
            commentDao.insert(comment);
        } catch (Exception e) {
            throw new SmartException(ResponseCode.FAILURE, e);
        }

    }
}
