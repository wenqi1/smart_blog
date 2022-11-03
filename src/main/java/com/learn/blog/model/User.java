package com.learn.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * 用户
 */
@Table(name = "wq_user")
@Data
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private String nickname;
    private String password;
    private String email;
    private String avatar;
    private Date createTime;
    private Date updateTime;
    private Date birthday;
    private int age;
    private String phone;
}

