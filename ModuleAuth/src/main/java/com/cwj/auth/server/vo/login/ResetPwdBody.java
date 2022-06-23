package com.cwj.auth.server.vo.login;

import lombok.Data;

import java.io.Serializable;

/**
 * ResetPwdBody 修改密码
 *
 * @author ChengWenjia
 * @since 2022-02-18 14:38
 */
@Data
public class ResetPwdBody implements Serializable {

    private static final long serialVersionUID = 1L;

    private String verifyCode;
    private String mail;
    private String newPassword;
}
