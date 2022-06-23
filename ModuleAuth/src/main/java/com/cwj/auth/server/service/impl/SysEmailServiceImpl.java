package com.cwj.auth.server.service.impl;


import com.cwj.auth.server.service.SysEmailService;
import com.cwj.common.Constants.Constants;
import com.cwj.common.base.result.ResultBase;
import com.cwj.common.base.result.ResultUtils;
import com.cwj.common.utils.DateUtil;
import com.cwj.datasource.mysql.base.repository.SysUserRepository;
import com.cwj.datasource.redis.RedisCacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * SysEmailServiceImpl 邮箱操作
 *
 * @author ChengWenjia
 * @since 2022-02-14 09:48
 */
@Service
public class SysEmailServiceImpl implements SysEmailService {

    private final Logger logger = LoggerFactory.getLogger("SysEmailService ---->");

    /**
     * 验证码长度: 默认6位
     */
    @Value("${email.verifyCodeLength}")
    private int verifyCodeLength;

    /**
     * 验证码有效时长: 默认5分钟
     */
    @Value("${email.verifyCodeEffectTime}")
    private int verifyCodeEffectTime;

    /**
     * 自定义验证码邮件主题
     */
    @Value("${email.cusSubject}")
    private String cusSubject;

    /**
     * 使用@Value注入application.properties中指定的用户名
     */
    @Value("${spring.mail.username}")
    private String username;

    /**
     * 后门验证码
     */
    @Value("${email.universalCode}")
    private String universalCode;

    @Resource
    private RedisCacheUtil redisCacheUtil;

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private SysUserRepository sysUserRepository;

    /**
     * 发送含有验证码的邮箱
     *
     * @return 邮箱内容
     */
    @Override
    public ResultBase sendEmailVerifyCode(String mail) {
        ResultBase resultMap = null;

        String verifyCode = generateVerifyCode();
        SimpleMailMessage mailMessage = mailInfoConfig(mail, verifyCode);

        try {
            javaMailSender.send(mailMessage);
            //JSONObject jsonObject = new JSONObject();
            //jsonObject.put("verifyCode", verifyCode);
            resultMap = ResultUtils.success("验证码生成成功");
            redisCacheUtil.setCacheObject(mail, verifyCode, verifyCodeEffectTime, TimeUnit.MINUTES);
        } catch (MailSendException e) {
            resultMap = ResultUtils.errorData("当前邮箱异常，账号不存在。具体异常为：" + e.getMessage());
        } catch (Exception e) {
            resultMap = ResultUtils.errorData("邮件发送异常！具体异常为：" + e.getMessage());
        }

        return resultMap;
    }

    /**
     * 邮箱是否存在
     *
     * @param mail 邮箱
     * @return 结果
     */
    @Override
    public ResultBase emailExists(String mail) {
        ResultBase resultMap = null;
        if (sysUserRepository.existsByEmail(mail)) {
            resultMap = ResultUtils.errorData("当前邮箱已存在。");
        } else {
            resultMap = ResultUtils.success("当前邮箱为新邮箱。");
        }
        return resultMap;
    }

    /**
     * 校验邮箱验证码
     *
     * @param mail       邮箱
     * @param verifyCode 验证码
     * @return 结果
     */
    @Override
    public ResultBase verifyEmailCode(String mail, String verifyCode) {
        ResultBase resultMap = null;
        if (verifyCode.equals(universalCode)) {
            // 后门验证码
            resultMap = ResultUtils.success("邮箱验证成功。");
        } else {
            String currentVerifyCode = redisCacheUtil.getCacheObject(mail);
            if (currentVerifyCode == null) {
                resultMap = ResultUtils.errorData("邮箱验证码已超时或已使用，请重新获取。");
            } else {
                if (currentVerifyCode.equals(verifyCode)) {
                    resultMap = ResultUtils.success("邮箱验证成功。");
                    redisCacheUtil.setCacheObject(mail, null);
                } else {
                    resultMap = ResultUtils.errorData("邮箱验证码错误。");
                }
            }
        }
        return resultMap;
    }


    /**
     * 邮件内容配置
     *
     * @param mail       用户注册的邮箱
     * @param verifyCode 验证码
     * @return 邮件内容
     */
    private SimpleMailMessage mailInfoConfig(String mail, String verifyCode) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(mail);
        mailMessage.setSubject(cusSubject);
        mailMessage.setText("亲爱的用户：\n您的验证码为：" + verifyCode + " ，创建于 " + DateUtil.nowTime() + " UTC，验证码" + verifyCodeEffectTime + "分钟有效(若不是本人操作，可忽略该条邮件)。\n请勿将验证码泄露给任何人。\n\n 敬上\n Metacomm");
        return mailMessage;
    }


    /**
     * 生成随机6位数的验证码
     *
     * @return 验证码
     */
    private String generateVerifyCode() {
        Random random = new Random();
        StringBuilder verifyCodeStr = new StringBuilder();
        for (int i = 0; i < verifyCodeLength; i++) {
            int ran = random.nextInt(10);
            verifyCodeStr.append(ran);
        }
        return verifyCodeStr.toString();
    }

    /**
     * math/char 校验 验证码
     *
     * @param code 验证码
     * @param uuid uuid
     */
    public ResultBase validateCaptcha(String code, String uuid) {
        ResultBase result = null;
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCacheUtil.getCacheObject(verifyKey);
        redisCacheUtil.deleteObject(verifyKey);
        if (captcha == null) {
            result = ResultUtils.errorData("图片验证码时间已过期或已经使用，请重新获取。");
        } else if (StringUtils.hasLength(code)) {
            if (!code.equalsIgnoreCase(captcha)) {
                result = ResultUtils.errorData("图片验证码错误。");
            } else {
                result = ResultUtils.success();
            }
        } else {
            result = ResultUtils.errorData("图片验证码不能为空。");
        }
        return result;
    }
}
