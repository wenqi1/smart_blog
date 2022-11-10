package com.learn.blog.enums;

import lombok.Getter;

/**
 * 权限名称枚举
 */
@Getter
public enum PermissionNames {
    ROLE_MANAGER_ADD("role_manager_add"),
    ROLE_MANAGER_DELETE("role_manager_delete"),
    ROLE_MANAGER_UPDATE("role_manager_update"),
    ROLE_MANAGER_QUERY("role_manager_query"),
    USER_MANAGER_ADD("user_manager_add"),
    USER_MANAGER_DELETE("user_manager_delete"),
    USER_MANAGER_UPDATE("user_manager_update"),
    USER_MANAGER_QUERY("user_manager_query"),
    RESOURCE_MANAGER_ADD("resource_manager_add"),
    RESOURCE_MANAGER_DELETE("resource_manager_delete"),
    RESOURCE_MANAGER_UPDATE("resource_manager_update"),
    RESOURCE_MANAGER_QUERY("resource_manager_query");

    private final String name;

    PermissionNames(String name) {
        this.name = name;
    }
}
