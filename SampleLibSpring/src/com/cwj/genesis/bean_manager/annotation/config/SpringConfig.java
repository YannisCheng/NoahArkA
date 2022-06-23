package com.cwj.genesis.bean_manager.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * com.cwj.genesis.bean_manager.annotation.config
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-05-26 09:20
 */
// 将当前类作为Spring的配置文件
@Configuration
@ComponentScan(basePackages = {"com.cwj.genesis.bean_manager.annotation"})
public class SpringConfig {
}
