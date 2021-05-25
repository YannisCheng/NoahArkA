package com.cwj.genesis.annotation;

import org.springframework.stereotype.Component;

/**
 * com.cwj.genesis.annotation
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-05-25 23:16
 */
//@Component(value = "userAnnotation")
//默认值：userAnno
@Component
public class UserAnno {
    public void showLog() {
        System.out.println("user .........");
    }
}
