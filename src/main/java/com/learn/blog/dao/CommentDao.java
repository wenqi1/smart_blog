package com.learn.blog.dao;

import com.learn.blog.model.Comment;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 评论dao层
 */
@Repository
public interface CommentDao extends Mapper<Comment> {
}
