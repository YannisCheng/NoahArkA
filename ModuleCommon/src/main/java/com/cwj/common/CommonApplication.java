package com.cwj.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 被依赖
 */
// 为了能让 Spring 找到 WebFilter，需要在启动类上加上该注释
@ServletComponentScan
@SpringBootApplication
public class CommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }

}
