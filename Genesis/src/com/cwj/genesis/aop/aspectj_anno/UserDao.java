package com.cwj.genesis.aop.aspectj_anno;

import org.springframework.stereotype.Component;

/**
 * com.cwj.genesis.aop.aspectj
 * 被增强类
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-05-26 16:22
 */
@Component
public class UserDao {
    public void add(){
        System.out.println("add ......");
    }
}
