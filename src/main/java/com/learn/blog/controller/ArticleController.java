package com.learn.blog.controller;

import com.learn.blog.dto.ArticlePushDto;
import com.learn.blog.dto.ArticleSaveDto;
import com.learn.blog.enums.ResponseCode;
import com.learn.blog.model.BasicResponse;
import com.learn.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章controller层
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 保存文章
     *
     * @param articleSaveDto ArticleSaveDto
     * @return BasicResponse
     */
    @PostMapping("/add")
    public BasicResponse addArticle(@Validated ArticleSaveDto articleSaveDto) {
        articleService.addArticle(articleSaveDto);
        return new BasicResponse(ResponseCode.SUCCESS);
    }

    /**
     * 发布文章
     *
     * @param articlePushDto ArticlePushDto
     * @return BasicResponse
     */
    @PostMapping("/push")
    public BasicResponse pushArticle(@Validated ArticlePushDto articlePushDto) {
        articleService.pushArticle(articlePushDto);
        return new BasicResponse(ResponseCode.SUCCESS);
    }



}
