package com.learn.blog.dao;

import com.learn.blog.model.ArticleCategoryLink;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 文章与分类关系dao层
 */
@Repository
public interface ArticleCategoryLinkDao extends Mapper<ArticleCategoryLink> {

}
