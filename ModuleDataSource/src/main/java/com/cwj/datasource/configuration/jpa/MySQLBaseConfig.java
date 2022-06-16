package com.cwj.datasource.configuration.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Objects;

/**
 * MySQLBaseConfig base JPA配置
 *
 * @author ChengWenjia
 * @since 2022-06-15 22:13
 */
@Configuration
//1、实体扫描
@EntityScan(basePackages = "com.cwj.datasource.mysql.base.entity")
//2、实体管理ref
@EnableJpaRepositories(
        basePackages = "com.cwj.datasource.mysql.base.repository",
        entityManagerFactoryRef = "baseEntityManagerFactory",
        transactionManagerRef = "baseTransactionManager")
//3、事务管理
@EnableTransactionManagement
public class MySQLBaseConfig {
    @Resource(name = "dataSourceBase")
    private DataSource dataSource;

    @Resource
    private JpaProperties jpaProperties;

    @Resource
    private EntityManagerFactoryBuilder entityManagerFactoryBuilder;

    @Primary
    @Bean(name = "baseEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean baseEntityManagerFactoryBean() {
        return entityManagerFactoryBuilder.dataSource(dataSource)
                //加入jpa的其他配置参数比如（ddl-auto: update等）
                .properties(jpaProperties.getProperties())
                //相当于给这个配置取一个别名
                .persistenceUnit("baseConfig")
                //设置这个数据源对应的实体类所在位置
                .packages("com.cwj.datasource.mysql.base")
                .build();
    }

    @Primary
    @Bean(name = "baseManager")
    public EntityManager fnClassManager() {
        return Objects.requireNonNull(baseEntityManagerFactoryBean().getObject()).createEntityManager();
    }

    @Bean(name = "baseTransactionManager")
    public PlatformTransactionManager baseTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(baseEntityManagerFactoryBean().getObject()));
    }
}
