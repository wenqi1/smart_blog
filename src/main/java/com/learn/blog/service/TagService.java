package com.learn.blog.service;

import com.learn.blog.model.Tag;

/**
 * 标签service层
 */
public interface TagService {
    /**
     * 保存标签
     *
     * @param tag Tag
     */
   void addTag(Tag tag);

}
