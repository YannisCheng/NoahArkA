package com.cwj.genesis.aop.aspectj_anno;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * com.cwj.genesis.aop.aspectj_anno
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-05-26 17:32
 */
public class AspectJTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringScan.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);
        userDao.add();
        /*
         * 运行结果：
         * before 。。。。。
         * add ......
         * after 。。。。。
         */
    }
}
