package com.learn.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 角色与资源关系
 */
@Table(name = "wq_role_resource_link")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResourceLink {
    private Long id;
    @NotNull
    private Long roleId;
    @NotNull
    private Long resourceId;
}
