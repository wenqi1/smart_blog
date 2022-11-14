package com.learn.blog.controller;

import com.learn.blog.enums.ResponseCode;
import com.learn.blog.model.BasicResponse;
import com.learn.blog.model.Comment;
import com.learn.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论controller层
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     *
     * @param comment Comment
     * @return BasicResponse
     */
    @PostMapping("/add")
    public BasicResponse addRole(@Validated Comment comment) {
        commentService.addComment(comment);
        return new BasicResponse(ResponseCode.SUCCESS);
    }


}
