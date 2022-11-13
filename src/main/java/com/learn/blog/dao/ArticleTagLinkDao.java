package com.learn.blog.dao;

import com.learn.blog.model.ArticleTagLink;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 文章与标签关系dao层
 */
@Repository
public interface ArticleTagLinkDao extends Mapper<ArticleTagLink> {

}
