package com.cwj.auth.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * CaptchaParam 图片验证码配置参数
 *
 * @author ChengWenjia
 * @date 2022-02-16 10:43
 */
@Data
@Configuration
public class CaptchaParam {

    @Value("${captcha.switchOn}")
    private boolean switchOn;

    @Value("${captcha.captchaType}")
    private String captchaType;

    @Value("${captcha.expiration}")
    private int expiration;


}
