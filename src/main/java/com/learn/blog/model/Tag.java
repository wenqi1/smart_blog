package com.learn.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * 标签
 */
@Table(name = "wq_tag")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private Long id;
    @NotEmpty
    private String name;
    private String description;
    private Date createTime;
}
