package com.learn.blog.controller;

import com.learn.blog.config.JwtConfig;
import com.learn.blog.enums.ResponseCode;
import com.learn.blog.model.APIResponse;
import com.learn.blog.model.BasicResponse;
import com.learn.blog.model.Resource;
import com.learn.blog.model.User;
import com.learn.blog.service.PermissionService;
import com.learn.blog.service.UserService;
import com.learn.blog.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户controller层
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private JwtConfig jwtConfig;

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
    public APIResponse<String> login(String phone, String userPassword) {
        User user = userService.login(phone, userPassword);
        // 获取用户的所有资源
        List<Resource> resources = permissionService.queryUserPermission(user.getId());
        // 资源的code集合
        List<String> resourceCodes = resources.stream().map(Resource::getCode).collect(Collectors.toList());
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(jwtConfig.getPermission(), resourceCodes);
        // 生成token
        String token = JwtUtils.generateToken(user.getName(), claims, jwtConfig.getExpiration(), jwtConfig.getSalt());
        return new APIResponse<>(ResponseCode.SUCCESS, token);
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
