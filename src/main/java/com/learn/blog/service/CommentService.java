package com.learn.blog.service;

import com.learn.blog.model.Comment;

/**
 * 评论service层
 */
public interface CommentService {
    /**
     * 添加评论
     *
     * @param comment Comment
     */
   void addComment(Comment comment);

}
