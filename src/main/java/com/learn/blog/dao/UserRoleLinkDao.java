package com.learn.blog.dao;

import com.learn.blog.model.UserRoleLink;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 用户与角色关系dao层
 */
@Repository
public interface UserRoleLinkDao extends Mapper<UserRoleLink> {

}
