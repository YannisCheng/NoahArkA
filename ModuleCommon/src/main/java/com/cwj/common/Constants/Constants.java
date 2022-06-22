package com.cwj.common.Constants;

/**
 * Constants 通用常量信息
 *
 * @author ChengWenjia
 * @since 2022-06-17 14:00
 */
public class Constants {
    /**
     * 资源映射路径 前缀-压缩包
     */
    public static final String RESOURCE_PREFIX_ZIP = "/profileZip/";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "success";

    public static final String IM_USER = "IMUser";

    public static final String USER_MODEL = "userModel";

    public static final String IM_EXCEPTION = "IMException";

    public static final String RESULT_TAG = "result";

    public static final String MSG_TAG = "msg";

    public static final int NEW_TAG = 1;

    public static final int NOT_NEW_TAG = 0;

    public static final String IM_SERVER_EXCEPTION = "IM服务器网络或接口异常";

    /**
     * 唯一编码 type 取值
     */
    public static final String TYPE_FACE = "type_face";
    public static final String TYPE_ICON = "type_icon";
    public static final String TYPE_BACKGROUND = "type_background";


    /**
     * 登录、注册 方式1：邮箱
     */
    public static final int TAG_MAIL = 1;

    /**
     * 登录、注册 方式2：手机
     */
    public static final int TAG_PHONE = 2;

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "fail:";

    /**
     * 令牌
     */
    public static final String TOKEN = "token";


    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 邮箱使用时间格式
     */
    public static final String DATA_PATTERN = "yyyyMMddHHmmss";
}
