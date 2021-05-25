package com.cwj.apprun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * IndexController
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-05-24 22:34 星期一
 */
@RestController
@ComponentScan(basePackages = "com.cwj.configuration")
public class IndexController {

    @Autowired
    DataSource dataSource;

    @GetMapping("/say")
    @ResponseBody
    public String say() throws SQLException {
        contextLoads();
        return "say: Hello word";
    }

    void contextLoads() throws SQLException {
        //是否获取到数据源
        System.out.println(dataSource.getClass());
        //获取一个连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
