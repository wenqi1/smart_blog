package com.learn.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 资源
 */
@Table(name = "wq_resource")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String code;
    @NotNull
    private Long parentId;
    @NotNull
    private Integer nodeType;
    private String iconUrl;
    @NotNull
    private Integer sort;
    private String url;
    private Integer level;
    private String path;
}
