package com.learn.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * 文章与分类关系
 */
@Table(name = "wq_article_category_link")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCategoryLink {
    private Long id;
    private Long articleId;
    private Long categoryId;
}
