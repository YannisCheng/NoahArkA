package com.cwj.datasource.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * com.cwj.datasource.redis
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-05 11:42
 */
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

    /**
     * RedisTemplate的配置
     * @param lettuceConnectionFactory 连接工厂类
     * @return 已配置的对象
     */
    @Bean
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
