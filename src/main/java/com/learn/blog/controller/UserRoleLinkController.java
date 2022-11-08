package com.learn.blog.controller;

import com.learn.blog.enums.ResponseCode;
import com.learn.blog.model.BasicResponse;
import com.learn.blog.model.UserRoleLink;
import com.learn.blog.service.UserRoleLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户与角色关系controller层
 */
@RestController
@RequestMapping("/userRoleLink")
public class UserRoleLinkController {

    @Autowired
    private UserRoleLinkService userRoleLinkService;

    /**
     * 添加用户与角色关系
     *
     * @param userRoleLink userRoleLink
     * @return BasicResponse
     */
    @PostMapping("/add")
    public BasicResponse addUserRoleLink(@Validated UserRoleLink userRoleLink) {
        userRoleLinkService.addUserRoleLink(userRoleLink);
        return new BasicResponse(ResponseCode.SUCCESS);
    }


}
