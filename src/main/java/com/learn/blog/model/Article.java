package com.learn.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 文章
 */
@Table(name = "wq_article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Long id;
    @NotNull
    private Long userId;
    @NotEmpty
    private String title;
    @NotEmpty
    private String summary;
    private Integer likeCount;
    private Integer commentCount;
    private Integer readCount;
    private Integer topFlag;
    private Date createTime;
    private Date updateTime;
    private Date pushTime;
}
