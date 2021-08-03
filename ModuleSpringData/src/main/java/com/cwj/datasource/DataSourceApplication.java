package com.cwj.datasource;

import com.cwj.common.CommonApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

// SpringBoot启动入口
@SpringBootApplication
// 为Mybatis设置Mapping的扫描范围
@MapperScan(basePackages = "com.cwj.datasource.mysql.dao")
// 添加 Common Module 的容器入口依赖
@Import(value = {CommonApplication.class})
public class DataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSourceApplication.class, args);
    }

}
