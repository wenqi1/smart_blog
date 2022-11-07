package com.learn.blog.controller;

import com.learn.blog.enums.ResponseCode;
import com.learn.blog.model.BasicResponse;
import com.learn.blog.model.Role;
import com.learn.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色controller层
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 添加角色
     *
     * @param role role
     * @return BasicResponse
     */
    @PostMapping("/add")
    public BasicResponse addRole(@Validated Role role) {
        roleService.addRole(role);
        return new BasicResponse(ResponseCode.SUCCESS);
    }


}
