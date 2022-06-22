package com.cwj.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * MetaServerConfig Meta服务端配置
 *
 * @author ChengWenjia
 * @date 2022-02-10 09:46
 */
@Component
public class MetaServerConfig {

    private static String profile;
    /**
     * 文件上传路径: 压缩包
     */
    @Value("${noahArka.profilePath}")
    private String profilePath;

    public static String getProfile() {
        return profile;
    }

    public static String getUploadPath() {
        return profile + "scenes/";
    }

    /**
     * 将yml中的值赋值给static变量
     */
    @PostConstruct
    public void getApiToken() {
        profile = this.profilePath;
    }
}
