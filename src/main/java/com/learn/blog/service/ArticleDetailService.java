package com.learn.blog.service;

import com.learn.blog.model.ArticleDetail;

/**
 * 文章详情service层
 */
public interface ArticleDetailService {
    /**
     * 保存文章详情
     *
     * @param articleDetail ArticleDetail
     */
   void addArticleDetail(ArticleDetail articleDetail);

}
