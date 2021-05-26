package com.cwj.genesis.aop.aspectj_anno;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * com.cwj.genesis.aop.aspectj_anno
 * 增强类
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-05-26 16:49
 */
// 创建Aspect代理对象
@Aspect
@Component
public class UserDaoProxy {

    //前置通知：value值可忽略
    @Before(value = "execution(* com.cwj.genesis.aop.aspectj_anno.UserDao.add())")
    public void before(){
        System.out.println("before 。。。。。");
    }

    @After(value = "execution(* com.cwj.genesis.aop.aspectj_anno.UserDao.add())")
    public void after(){
        System.out.println("after 。。。。。");
    }
}
