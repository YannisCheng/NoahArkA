package com.cwj.genesis.aop.aspectj_anno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * com.cwj.genesis.aop.aspectj_anno
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-05-26 16:53
 */
//将此类声明为一个配置文件
@Configuration
// 开启AspectJ自动生成代理对象
@EnableAspectJAutoProxy
// 配置注解扫描路径范围
@ComponentScan(basePackages = {"com.cwj.genesis.aop"})
public class SpringScan {
}
