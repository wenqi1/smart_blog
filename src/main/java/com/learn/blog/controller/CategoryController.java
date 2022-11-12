package com.learn.blog.controller;

import com.learn.blog.enums.ResponseCode;
import com.learn.blog.model.BasicResponse;
import com.learn.blog.model.Category;
import com.learn.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分类controller层
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 添加分类
     *
     * @param category Category
     * @return BasicResponse
     */
    @PostMapping("/add")
    public BasicResponse addCategory(@Validated Category category) {
        categoryService.addCategory(category);
        return new BasicResponse(ResponseCode.SUCCESS);
    }


}
