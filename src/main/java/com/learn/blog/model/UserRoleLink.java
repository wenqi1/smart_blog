package com.learn.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * 用户与角色的关系
 */
@Table(name = "wq_user_role_link")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleLink {
    private Long id;
    @NotEmpty
    private Long userId;
    @NotEmpty
    private Long roleId;
}
