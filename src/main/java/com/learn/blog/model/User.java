package com.learn.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * 用户
 */
@Table(name = "wq_user")
@Data
@AllArgsConstructor
public class User {
    private Long id;
    @NotEmpty
    private String name;
    private String nickname;
    @NotEmpty
    private String password;
    private String email;
    private String avatar;
    private Date createTime;
    private Date updateTime;
    private Date birthday;
    private Integer age;
    @NotEmpty
    private String phone;
}

