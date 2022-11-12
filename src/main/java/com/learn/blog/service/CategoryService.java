package com.learn.blog.service;

import com.learn.blog.model.Category;

/**
 * 分类service层
 */
public interface CategoryService {
    /**
     * 保存分类
     *
     * @param category Category
     */
   void addCategory(Category category);

}
