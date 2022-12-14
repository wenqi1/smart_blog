package com.learn.blog.dao;

import com.learn.blog.model.Role;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 角色dao层
 */
@Repository
public interface RoleDao extends Mapper<Role> {
    /**
     * 根据主键查找角色
     *
     * @param id 主键
     * @return Role
     */
    Role queryRoleById(Long id);
}
