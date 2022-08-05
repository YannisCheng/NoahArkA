package com.cwj.auth.server.service;


import com.cwj.common.result.ResultBase;

/**
 * SysEmailService 邮箱验证码操作
 *
 * @author ChengWenjia
 * @since 2022-02-14 09:46
 */
public interface SysEmailService {

    /**
     * 发送含有验证码的邮件
     *
     * @return 结果
     */
    ResultBase sendEmailVerifyCode(String mail);

    /**
     * 邮箱是否已经存在
     *
     * @return 结果
     */
    ResultBase emailExists(String mail);

    /**
     * 校验邮箱验证码
     *
     * @return 结果
     */
    ResultBase verifyEmailCode(String mail, String verifyCode);


    ResultBase validateCaptcha(String code, String uuid);
}
