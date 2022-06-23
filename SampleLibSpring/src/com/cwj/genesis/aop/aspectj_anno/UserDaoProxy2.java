package com.cwj.genesis.aop.aspectj_anno;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * com.cwj.genesis.aop.aspectj_anno
 * 增强类
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-05-26 16:49
 */
// 创建Aspect代理对象
@Aspect
@Order(1)
@Component
public class UserDaoProxy2 {

    // 相同的切入点抽取
    @Pointcut(value = "execution(* com.cwj.genesis.aop.aspectj_anno.UserDao.add())")
    public void point(){

    }

    //前置通知：value值可忽略
    @Before(value = "point()")
    public void before(){
        System.out.println(" 22222 before 。。。。。");
    }


}
