package com.learn.blog.controller;

import com.learn.blog.enums.ResponseCode;
import com.learn.blog.model.BasicResponse;
import com.learn.blog.model.RoleResourceLink;
import com.learn.blog.service.RoleResourceLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色与资源关系controller层
 */
@RestController
@RequestMapping("/roleResourceLink")
public class RoleResourceLinkController {

    @Autowired
    private RoleResourceLinkService roleResourceLinkService;

    /**
     * 添加角色与资源关系
     *
     * @param roleResourceLink roleResourceLink
     * @return BasicResponse
     */
    @PostMapping("/add")
    public BasicResponse addUserRoleLink(@Validated RoleResourceLink roleResourceLink) {
        roleResourceLinkService.addRoleResourceLink(roleResourceLink);
        return new BasicResponse(ResponseCode.SUCCESS);
    }


}
