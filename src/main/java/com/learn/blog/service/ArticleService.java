package com.learn.blog.service;

import com.learn.blog.dto.ArticlePushDto;
import com.learn.blog.dto.ArticleSaveDto;

/**
 * 文章service层
 */
public interface ArticleService {
    /**
     * 保存文章
     *
     * @param articleSaveDto ArticleDto
     */
   void addArticle(ArticleSaveDto articleSaveDto);

    /**
     * 发布文章
     *
     * @param articlePushDto ArticlePushDto
     */
   void pushArticle(ArticlePushDto articlePushDto);

}
