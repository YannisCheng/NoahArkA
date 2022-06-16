package com.cwj.datasource.configuration.jpa;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * JpaDataSourceConfig JPA-MySQL 多数据源配置中心
 *
 * @author ChengWenjia
 * @since 2022-06-15 22:11
 */
@Configuration
public class JpaDataSourceConfig {

    // ----------------------- 主数据源配置-Base -----------------------
    @Primary
    @Bean(name = "dataSourceBase")
    @ConfigurationProperties(prefix = "spring.datasource.base")
    public DataSource dataSourceBase(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "baseJdbcTemplate")
    public JdbcTemplate getJdbcTemplateBase(@Qualifier("dataSourceBase") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    // ----------------------- 数据源配置-49Class -----------------------
    @Bean(name = "dataSource49Class")
    @ConfigurationProperties(prefix = "spring.datasource.fnclass")
    public DataSource dataSource49Class(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "49JdbcTemplate")
    public JdbcTemplate getJdbcTemplate49(@Qualifier("dataSource49Class") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    // ----------------------- 数据源配置-Area -----------------------
    @Bean(name = "dataSourceArea")
    @ConfigurationProperties(prefix = "spring.datasource.area")
    public DataSource dataSourceArea(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "areaJdbcTemplate")
    public JdbcTemplate getJdbcTemplateArea(@Qualifier("dataSourceArea") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
