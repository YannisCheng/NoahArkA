package com.cwj.auth.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.cwj.auth.config.CaptchaParam;
import com.cwj.auth.server.service.SysEmailService;
import com.cwj.common.Constants.Constants;
import com.cwj.common.base.result.ResultBase;
import com.cwj.common.base.result.ResultUtils;
import com.cwj.common.utils.Base64;
import com.cwj.common.utils.IdUtils;
import com.cwj.datasource.redis.RedisCacheUtil;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * CaptchaController  验证码操作处理
 *
 * @author ChengWenjia
 * @date 2022/2/16 10:36
 */
@RestController
@Api(tags = "验证码相关")
public class CaptchaController {
    @Autowired
    SysEmailService sysEmailService;

    @Autowired
    CaptchaParam captchaParam;

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    @ApiOperation(value = "captchaImage", notes = "生成图片验证码。如果不传递fromTag参数值，则接口返回结果中code值，fromTag可传递任意字符串。")
    public ResultBase getCode(@RequestParam(name = "fromTag", required = false) String fromTag) throws IOException {
        ResultBase resultMap = null;
        if (captchaParam.isSwitchOn()) {
            JSONObject jsonObject = new JSONObject();
            // 开启验证码
            // 保存验证码信息
            String uuid = IdUtils.simpleUUID();
            String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

            String capStr = null, code = null;
            BufferedImage image = null;

            // 生成验证码
            String captchaType = captchaParam.getCaptchaType();
            if ("math".equals(captchaType)) {
                // "3*7=?@21"
                String capText = captchaProducerMath.createText();
                // "3*7=?"
                capStr = capText.substring(0, capText.lastIndexOf("@"));
                // 21
                code = capText.substring(capText.lastIndexOf("@") + 1);
                image = captchaProducerMath.createImage(capStr);
            } else if ("char".equals(captchaType)) {
                // aqqa
                capStr = code = captchaProducer.createText();
                image = captchaProducer.createImage(capStr);
            }

            redisCacheUtil.setCacheObject(verifyKey, code, captchaParam.getExpiration(), TimeUnit.MINUTES);

            // 转换流信息写出
            FastByteArrayOutputStream os = new FastByteArrayOutputStream();
            try {
                ImageIO.write(image, "jpg", os);
            } catch (IOException e) {
                System.out.println("ImageIo exception: " + e.toString());
                resultMap = ResultUtils.errorData(e.toString());
            }

            jsonObject.put("uuid", uuid);
            if (fromTag == null) {
                jsonObject.put("captcha", code);
            }
            jsonObject.put("img", Base64.encode(os.toByteArray()));
            resultMap = ResultUtils.success(jsonObject);
        } else {
        }


        return resultMap;
    }

    @GetMapping("/verifyCaptchaImage")
    @ApiOperation(value = "verifyCaptchaImage", notes = "图片验证码验证接口")
    public ResultBase verifyCaptchaImage(@RequestParam(name = "code") String code, @RequestParam(name = "uuid") String uuid) throws IOException {
        System.out.println("code : " + code + ", uuid: " + uuid);
        return sysEmailService.validateCaptcha(code, uuid);
    }
}
