package com.learn.blog.dao;

import com.learn.blog.model.Category;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 分类dao层
 */
@Repository
public interface CategoryDao extends Mapper<Category> {
    /**
     * 根据主键查找分类
     *
     * @param id 主键
     * @return Category
     */
    Category queryCategoryById(Long id);
}
