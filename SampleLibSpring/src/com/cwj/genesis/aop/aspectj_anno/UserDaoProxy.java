package com.cwj.genesis.aop.aspectj_anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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
@Order(3)
@Component
public class UserDaoProxy {

    // 相同的切入点抽取
    @Pointcut(value = "execution(* com.cwj.genesis.aop.aspectj_anno.UserDao.add())")
    public void point(){

    }

    //前置通知：value值可忽略
    @Before(value = "point()")
    public void before(){
        System.out.println("before 。。。。。");
    }

    /**
     * 最终通知
     */
    @After(value = "point()")
    public void after(){
        System.out.println("after 。。。。。");
    }

    /**
     * 返回通知
     */
    @AfterReturning(value = "point()")
    public void afterReturning(){
        System.out.println("afterReturning 。。。。。");
    }

    /**
     * 异常通知
     */
    @AfterThrowing(value = "point()")
    public void afterThrowing(){
        System.out.println("afterThrowing 。。。。。");
    }

    @Around(value = "execution(* com.cwj.genesis.aop.aspectj_anno.UserDao.add())")
    public void Around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("Around before。。。。。");
        // 被增强的方法
        point.proceed();
        System.out.println("Around after。。。。。");
    }
}
