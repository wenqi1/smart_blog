package com.learn.blog.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 发布文章dto
 */
@Getter
@Setter
public class ArticlePushDto {
    @NotNull
    private Long articleId;
    @NotNull
    private Long tagId;
    @NotNull
    private Long categoryId;
}
