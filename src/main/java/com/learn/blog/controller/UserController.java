package com.learn.blog.controller;

import com.learn.blog.enums.ResponseCode;
import com.learn.blog.model.BasicResponse;
import com.learn.blog.model.User;
import com.learn.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param user 用户
     * @return BasicResponse
     */
    @PostMapping("/register")
    public BasicResponse register(@Validated User user) {
        userService.register(user);
        return new BasicResponse(ResponseCode.SUCCESS);
    }

    /**
     * 登录
     *
     * @param phone 手机号
     * @param userPassword 密码
     * @return BasicResponse
     */
    @GetMapping("/login")
    public BasicResponse login(String phone, String userPassword) {
        User user = userService.login(phone, userPassword);
        return new BasicResponse(ResponseCode.SUCCESS);
    }

    /**
     * 修改
     *
     * @param user user
     * @return BasicResponse
     */
    @PostMapping("/update")
    public BasicResponse update(User user) {
        userService.update(user);
        return new BasicResponse(ResponseCode.SUCCESS);
    }


}
