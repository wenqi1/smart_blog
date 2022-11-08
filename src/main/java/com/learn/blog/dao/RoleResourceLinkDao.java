package com.learn.blog.dao;

import com.learn.blog.model.RoleResourceLink;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 角色与资源关系dao层
 */
@Repository
public interface RoleResourceLinkDao extends Mapper<RoleResourceLink> {

}
