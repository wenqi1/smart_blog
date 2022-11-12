package com.learn.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 分类
 */
@Table(name = "wq_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Long id;
    @NotEmpty
    private String name;
    private String description;
    @NotNull
    private Long parentId;
    @NotNull
    private Long userId;
    private Date createTime;
}
