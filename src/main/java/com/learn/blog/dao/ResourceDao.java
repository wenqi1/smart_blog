package com.learn.blog.dao;

import com.learn.blog.model.Resource;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 资源dao层
 */
@Repository
public interface ResourceDao extends Mapper<Resource> {
    /**
     * 根据主键查找资源
     *
     * @param id 主键
     * @return Resource
     */
    Resource queryResourceById(Long id);
}
