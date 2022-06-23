package com.cwj.datasource.configuration.mybatis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * com.cwj.datasource.configuration.mybatis.MySQL49ClassDsConfigBean 49class人员数据库
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-08-31 16:09
 */
//@Data
//@Configuration
//@PropertySource(value = "classpath:application-data-dev.yml", encoding = "UTF-8")
//@ConfigurationProperties(prefix = "spring.datasource.49class")
public class MySQL49ClassDsConfigBean {

    private String url;
    private String password;
    private String username;
    private String driverClassName;


    /**
     * 配置数据源
     *
     * @return DataSource
     */
    //@Bean(name = "setDataSource")
    //@Primary
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
    //@Bean(name = "firstJdbcTemplate")
    public JdbcTemplate getJdbcTemplate(@Qualifier("setDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
