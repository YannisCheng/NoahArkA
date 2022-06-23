package com.cwj.datasource.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * com.cwj.datasource.configuration.RedisIndex0DsConfigBean Redis-索引为0的数据库 配置bean
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-09-01 20:03
 */
@Data
@Configuration
@PropertySource(value = "classpath:application-data-dev.yml", encoding = "UTF-8")
@ConfigurationProperties(value = "spring.redis0")
public class RedisIndex0DsConfigBean {
    private String host;
    private int port;
    private int database;
}
