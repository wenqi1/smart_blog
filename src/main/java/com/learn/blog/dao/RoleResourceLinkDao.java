package com.learn.blog.dao;

import com.learn.blog.model.RoleResourceLink;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 角色与资源关系dao层
 */
@Repository
public interface RoleResourceLinkDao extends Mapper<RoleResourceLink> {
    /**
     * 批量查询指定角色的资源关系
     *
     * @param roleIds 角色id
     * @return List<RoleResourceLink>
     */
    List<RoleResourceLink> queryRoleResourceLinkByRoleIds(List<Long> roleIds);
}
