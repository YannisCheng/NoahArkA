package com.cwj.datasource.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * com.cwj.common.interceptor
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-23 15:58
 */
// 表示它是一个切面
@Aspect
@Component
public class AspectOfAop {
    private final Logger logger = LoggerFactory.getLogger(AspectOfAop.class);

    public static final String AOP_TAG = "AOP --> ";

    /**
     * Pointcut是织入Advice的触发条件。
     * 每个Pointcut的定义包括2部分：
     * 1.表达式；
     * 2.方法签名，方法签名必须是public及void型。
     * <p>
     * 可以将Pointcut中的方法看作是一个被Advice引用的助记符，
     * 因为表达式不直观，因此我们可以通过方法签名的方式为此表达式命名。
     * 因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
     * <p>
     * 参数说明：`*` 表示所有，`..` 表示任意参数。1个`*`表示1层目录，从后向前推目录层级。
     */
    @Pointcut("execution(* com.cwj.datasource.elasticsearch.controller.EsSearchCenter.*(..))")
    public void aboutElasticSearch() {

    }

    /**
     * 前置增强，目标方法执行之前执行
     */
    @Before("aboutElasticSearch()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println(AOP_TAG + "doBefore，joinPoint：" + joinPoint.toString());
    }

    /**
     * 环绕增强，目标方法执行前后分别执行一些代码
     */
    @Around("aboutElasticSearch()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(AOP_TAG + "doAround，pjp：" + pjp.toString());
        long startTime = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println(AOP_TAG + "方法名：" + pjp.getSignature().getName() + "，方法执行耗时：" + (endTime - startTime));
        return proceed;
    }

    /**
     * 后置增强，不管是抛出异常或者正常退出都会执行
     */
    @After("aboutElasticSearch()")
    public void doAfter() {
        System.out.println(AOP_TAG + "doAfter");
    }

    /**
     * 返回增强，目标方法正常执行完毕时执行
     * returning的值和doAfterReturning的参数名一致
     */
    @AfterReturning(returning = "ret", pointcut = "aboutElasticSearch()")
    public void doAfterReturn(Object ret) throws Throwable {
        System.out.println(AOP_TAG + "doAfterReturn：" + ret.toString() + "----> 该返回值已经被处理");
    }

    /**
     * 异常抛出增强，目标方法发生异常的时候执行
     */
    @AfterThrowing(throwing = "throwable", pointcut = "execution(* com.cwj.datasource.elasticsearch.controller.*.*(..))")
    public void doAfterThrowing(Throwable throwable) {
        System.out.println(AOP_TAG + "doAfterThrowing：" + throwable.toString());
    }

}
