package com.cwj.datasource.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * com.cwj.datasource.configuration.RedisIndex1DsConfigBean Redis-索引为1的数据库 配置bean
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-09-01 20:06
 */
@Data
@Configuration
@PropertySource(value = "classpath:application-data-dev.yml", encoding = "UTF-8")
@ConfigurationProperties(value = "spring.redis1")
public class RedisIndex1DsConfigBean {
    private String host;
    private int port;
    private int database;
}
