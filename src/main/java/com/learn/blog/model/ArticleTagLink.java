package com.learn.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * 文章与标签关系
 */
@Table(name = "wq_article_tag_link")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTagLink {
  private Long id;
  private Long articleId;
  private Long tagId;
}
