package com.cwj.datasource.configuration.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
 * MySQLAreaConfig area JPA配置
 *
 * @author ChengWenjia
 * @since 2022-06-15 22:13
 */
@Configuration
//1、实体扫描
@EntityScan(basePackages = "com.cwj.datasource.mysql.area.entity")
//2、实体管理ref
@EnableJpaRepositories(
        basePackages = "com.cwj.datasource.mysql.area.repository",
        entityManagerFactoryRef = "areaEntityManagerFactory",
        transactionManagerRef = "areaTransactionManager")
//3、事务管理
@EnableTransactionManagement
public class MySQLAreaConfig {
    @Resource(name = "dataSourceArea")
    private DataSource dataSource;

    @Resource
    private JpaProperties jpaProperties;

    @Resource
    private EntityManagerFactoryBuilder entityManagerFactoryBuilder;

    @Bean(name = "areaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean areaEntityManagerFactoryBean() {
        return entityManagerFactoryBuilder.dataSource(dataSource)
                //加入jpa的其他配置参数比如（ddl-auto: update等）
                .properties(jpaProperties.getProperties())
                //相当于给这个配置取一个别名
                .persistenceUnit("areaConfig")
                //设置这个数据源对应的实体类所在位置
                .packages("com.cwj.datasource.mysql.area")
                .build();
    }

    @Bean(name = "areaManager")
    public EntityManager areaManager() {
        return Objects.requireNonNull(areaEntityManagerFactoryBean().getObject()).createEntityManager();
    }

    @Bean(name = "areaTransactionManager")
    public PlatformTransactionManager areaTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(areaEntityManagerFactoryBean().getObject()));
    }
}
