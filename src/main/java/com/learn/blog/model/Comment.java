package com.learn.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 评论
 */
@Table(name = "wq_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private Date createTime;
    private Long replyUserId;
    private String replyUserName;
    private Long commentUserId;
    private String commentUserName;
    @NotNull
    private Long articleId;
    @NotEmpty
    private String content;
    @NotNull
    private Long parentId;
}
