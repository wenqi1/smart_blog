package com.learn.blog.dao;

import com.learn.blog.model.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 文章dao层
 */
@Repository
public interface ArticleDao extends Mapper<Article> {
    /**
     * 根据主键查找文章
     *
     * @param id 主键
     * @return Article
     */
    Article queryArticleById(Long id);

    /**
     * 根据主键更新文章
     *
     * @param article Article
     * @return 更新行数
     */
    int updateArticleById(@Param("article") Article article);
}
