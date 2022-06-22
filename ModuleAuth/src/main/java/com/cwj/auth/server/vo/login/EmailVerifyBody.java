package com.cwj.auth.server.vo.login;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * EmailVerifyBody 邮件验证码生成结果
 *
 * @author ChengWenjia
 * @date 2022-02-14 11:09
 */
@Data
@AllArgsConstructor
public class EmailVerifyBody implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 验证码
     */
    private String verifyCode;

}
