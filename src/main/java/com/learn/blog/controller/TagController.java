package com.learn.blog.controller;

import com.learn.blog.enums.ResponseCode;
import com.learn.blog.model.BasicResponse;
import com.learn.blog.model.Tag;
import com.learn.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标签controller层
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 添加标签
     *
     * @param tag Tag
     * @return BasicResponse
     */
    @PostMapping("/add")
    public BasicResponse addTag(@Validated Tag tag) {
        tagService.addTag(tag);
        return new BasicResponse(ResponseCode.SUCCESS);
    }


}
