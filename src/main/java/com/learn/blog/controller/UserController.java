package com.learn.blog.controller;

import com.learn.blog.enums.ResponseCode;
import com.learn.blog.model.APIResponse;
import com.learn.blog.model.BasicResponse;
import com.learn.blog.model.User;
import com.learn.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登陆
     *
     * @param userName 用户名
     * @param userPassword 密码
     * @return APIResponse<User>
     */
    @GetMapping("/login")
    public APIResponse<User> login(String userName, String userPassword) {
        // User user = userService.login(userName, userPassword);
        return new APIResponse<>(ResponseCode.SUCCESS, null);
    }

    /**
     * 注册
     *
     * @param user 用户
     * @return BasicResponse
     */
    @PostMapping("/register")
    public BasicResponse register(User user) {
        userService.register(user);
        return new BasicResponse(ResponseCode.SUCCESS);
    }


}
