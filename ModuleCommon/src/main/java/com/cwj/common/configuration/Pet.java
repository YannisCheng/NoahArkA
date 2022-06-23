package com.cwj.common.configuration;

import lombok.Data;

/**
 * com.cwj.common.configuration
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-08-04 10:12
 */
//@Component
//@PropertySource(value = {"classpath:student.yml"}, encoding = "UTF-8")
//@ConfigurationProperties(prefix = "student")
@Data
public class Pet {
    private String spices;
    private String spices2;
    private Integer age;
    private Integer age2;
}
