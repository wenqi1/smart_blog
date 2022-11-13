package com.learn.blog.dao;

import com.learn.blog.model.ArticleDetail;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 文章详情dao层
 */
@Repository
public interface ArticleDetailDao extends Mapper<ArticleDetail> {
    /**
     * 根据文章id查找文章详情
     *
     * @param articleId 文章id
     * @return ArticleDetail
     */
    ArticleDetail queryArticleDetailByArticleId(Long articleId);
}
