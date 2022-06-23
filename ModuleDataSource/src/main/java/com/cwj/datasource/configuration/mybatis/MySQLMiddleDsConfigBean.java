package com.cwj.datasource.configuration.mybatis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * com.cwj.datasource.configuration.mybatis.MySQLMiddleDsConfigBean MySQl数据库-中间数据保 存配置bean
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-09-01 20:00
 */
//@Data
//@Configuration
//@PropertySource(value = "classpath:application-data-dev.yml",encoding = "UTF-8")
//@ConfigurationProperties(prefix = "spring.datasource.middle")
public class MySQLMiddleDsConfigBean {
    private String url;
    private String password;
    private String username;
    private String driverClassName;

    /**
     * 配置数据源
     *
     * @return DataSource
     */
    //@Bean(name = "setThirdDataSource")
    public DataSource setThirdDataSource(){
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
    //@Bean(name = "thirdJdbcTemplate")
    public JdbcTemplate getJdbcTemplate(@Qualifier("setThirdDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
