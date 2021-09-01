package com.cwj.common.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * com.cwj.common.configuration
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-04 09:50
 */
@Component
@Data
// 导入配置文件。注意增加编码方式，否则可能中文乱码
@PropertySource(value = {"classpath:application-student.yml"}, encoding = "UTF-8")
// 进行属性映射
@ConfigurationProperties(prefix = "student")
public class Student {
    private String name;
    private Boolean isfemale;
    private Date birth;

    private List<Object> teachers;
    private Map<String, Object> grade;

    private Pet pets;
}
