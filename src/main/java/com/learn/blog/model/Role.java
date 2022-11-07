package com.learn.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * 角色
 */
@Table(name = "wq_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Long id;
    @NotEmpty
    private String code;
    @NotEmpty
    private String name;
    private Date createTime;
}
