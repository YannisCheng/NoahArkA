package com.cwj.datasource.redis;

import com.cwj.datasource.configuration.RedisIndex0DsConfigBean;
import com.cwj.datasource.configuration.RedisIndex1DsConfigBean;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * com.cwj.datasource.redis.RedisConfiguration Reids数据源及数据序列化模板的配置
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-05 11:42
 */
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

    /**
     * 引入 index0的Redis配置
     */
    @Resource
    RedisIndex0DsConfigBean redisIndex0DsConfigBean;

    /**
     * 引入 index1的Redis配置
     */
    @Resource
    RedisIndex1DsConfigBean redisIndex1DsConfigBean;

    @Bean
    @ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
    public GenericObjectPoolConfig redisPool(){
        return new GenericObjectPoolConfig<>();
    }

    /**
     * 配置第一个Redis服务
     * @param poolConfig GenericObjectPoolConfig
     * @return LettuceConnectionFactory
     */
    @Bean("dataFactory")
    // Primary：标识该redis为默认数据源
    @Primary
    public LettuceConnectionFactory dateFactory(GenericObjectPoolConfig poolConfig){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisIndex0DsConfigBean.getHost(), redisIndex0DsConfigBean.getPort());
        configuration.setDatabase(redisIndex0DsConfigBean.getDatabase());
        LettucePoolingClientConfiguration build = LettucePoolingClientConfiguration.builder().poolConfig(poolConfig).build();
        return new LettuceConnectionFactory(configuration,build);
    }

    /**
     * 配置第二个Redis服务
     * @param poolConfig GenericObjectPoolConfig
     * @return LettuceConnectionFactory
     */
    @Bean("secondDataFactory")
    public LettuceConnectionFactory secondDataFactory(GenericObjectPoolConfig poolConfig){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisIndex1DsConfigBean.getHost(), redisIndex1DsConfigBean.getPort());
        configuration.setDatabase(redisIndex1DsConfigBean.getDatabase());
        LettucePoolingClientConfiguration build = LettucePoolingClientConfiguration.builder().poolConfig(poolConfig).build();
        return new LettuceConnectionFactory(configuration,build);
    }

    /**
     * 设置 Redis0 对应的数据序列化方式
     * @param lettuceConnectionFactory redis0的连接池
     * @return RedisTemplate
     */
    @Bean("firstRedisDataTemplate")
    @Primary
    public RedisTemplate<String, Object> firstRedisDataTemplate(@Qualifier("dataFactory") LettuceConnectionFactory lettuceConnectionFactory){
        return redisTemplate(lettuceConnectionFactory);
    }

    /**
     * 设置 Redis1 对应的数据序列化方式
     * @param lettuceConnectionFactory redis1的连接池
     * @return RedisTemplate
     */
    @Bean("secondRedisDataTemplate")
    public RedisTemplate<String, Object> secondRedisDataTemplate(@Qualifier("secondDataFactory") LettuceConnectionFactory lettuceConnectionFactory){
        return redisTemplate(lettuceConnectionFactory);
    }

    /**
     * RedisTemplate的具体配置
     * @param lettuceConnectionFactory 连接工厂类
     * @return 已配置的对象
     */
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory){

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);

        StringRedisSerializer serializer = new StringRedisSerializer();
        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jsonRedisSerializer.setObjectMapper(objectMapper);

        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(jsonRedisSerializer);

        redisTemplate.setHashKeySerializer(serializer);
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);


        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
