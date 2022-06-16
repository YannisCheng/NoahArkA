package com.cwj.datasource.configuration.mybatis;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * com.cwj.datasource.configuration.mybatis.MySQLAreaDsConfigBean 行政区划数据库
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-09-01 19:53
 */
//@Data
//@Configuration
//@PropertySource(value = "classpath:application-data-dev.yml", encoding = "UTF-8")
//@ConfigurationProperties(prefix = "spring.datasource.area")
public class MySQLAreaDsConfigBean {

    private String url;
    private String password;
    private String username;
    private String driverClassName;

    /**
     * 配置数据源
     *
     * @return DataSource
     */
    //@Bean(name = "setSecondDataSource")
    public DataSource setDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    /**
     * 配置JdbcTemplate
     *
     * @param dataSource 数据源
     * @return JdbcTemplate
     */
    //@Bean(name = "secondJdbcTemplate")
    public JdbcTemplate getJdbcTemplate(@Qualifier("setSecondDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
