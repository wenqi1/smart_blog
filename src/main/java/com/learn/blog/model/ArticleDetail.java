package com.learn.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * 文章详情
 */
@Table(name = "wq_article_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetail {
    private Long id;
    private String contentMd;
    private String contentHtml;
    private Long articleId;
}
