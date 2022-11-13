package com.learn.blog.dto;

import com.learn.blog.model.Article;
import lombok.Getter;
import lombok.Setter;

/**
 * 保存文章dto
 */
@Getter
@Setter
public class ArticleSaveDto extends Article {
    private String contentMd;
    private String contentHtml;
}
