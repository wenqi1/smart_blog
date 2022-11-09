package com.learn.blog.dao;

import com.learn.blog.model.UserRoleLink;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 用户与角色关系dao层
 */
@Repository
public interface UserRoleLinkDao extends Mapper<UserRoleLink> {

    /**
     * 查询指定用户的角色关系
     *
     * @param userId 用户id
     * @return List<UserRoleLink>
     */
    List<UserRoleLink> queryUserRoleLinkByUserId(Long userId);
}
