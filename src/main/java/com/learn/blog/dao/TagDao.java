package com.learn.blog.dao;

import com.learn.blog.model.Tag;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 标签dao层
 */
@Repository
public interface TagDao extends Mapper<Tag> {
    /**
     * 根据主键查找标签
     *
     * @param id 主键
     * @return Tag
     */
    Tag queryTagById(Long id);
}
