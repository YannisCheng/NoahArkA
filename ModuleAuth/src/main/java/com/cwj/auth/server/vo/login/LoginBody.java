package com.cwj.auth.server.vo.login;

import lombok.Data;

import java.io.Serializable;

/**
 * LoginBody  用户登录实体对象
 *
 * @author ChengWenjia
 * @date 2022/2/10 09:30
 */
@Data
public class LoginBody implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户注册邮箱
     */
    private String userEmail;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 登录方式Tag
     * 1: 邮箱，2：手机号
     */
    private int loginTag = 1;

}
