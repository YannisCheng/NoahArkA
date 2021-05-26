package com.cwj.genesis.bean_manager;

import com.cwj.genesis.bean_manager.annotation.UserAnno;
import com.cwj.genesis.bean_manager.annotation.config.SpringConfig;
import com.cwj.genesis.bean_manager.xml.bean.BookStu;
import com.cwj.genesis.bean_manager.xml.bean.Emp;
import com.cwj.genesis.bean_manager.xml.bean.Stu;
import com.cwj.genesis.bean_manager.xml.declare.Book;
import com.cwj.genesis.bean_manager.xml.declare.User;
import com.cwj.genesis.bean_manager.xml.lifecycle.LifeCycleBean5;
import com.cwj.genesis.bean_manager.xml.lifecycle.LifeCycleBean7;
import com.cwj.genesis.bean_manager.xml.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * com.cwj.genesis.test
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-05-25 15:03
 */
public class MainTestSpring {

    public static void main(String[] args) {
        // 纯注解开发
        extractedOnlyAnno();

        // 非纯注解开发
        //extractedAnno1();
        //extractedBeanLifeCycle5();
        //extractedFactoryBean();
        //extractedMult3();
        //extractedMult2();
        //extractedMult();
        //extractedDeptEmp();
        //extractedUserDao();
        //extractedUser();
        //extractedBook();
    }

    /**
     * 纯注解开发使用
     */
    private static void extractedOnlyAnno() {
        // 加载配置类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        com.cwj.genesis.bean_manager.annotation.UserAnno bean = context.getBean("userAnno", UserAnno.class);
        bean.showLog();
    }

    private static void extractedAnno1() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Annotation1.xml");
        com.cwj.genesis.bean_manager.annotation.UserAnno bean = context.getBean("userAnno", UserAnno.class);
        bean.showLog();
    }

    private static void extractedBeanLifeCycle7() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("lifecycle7.xml");
        LifeCycleBean7 lifecycle = context.getBean("life7", LifeCycleBean7.class);
        System.out.println("6 step -> use lifecycle obj" + lifecycle.toString());

        // 手动调用Bean的销毁，调用：LifeCycleBean5#destroyMethod()
        context.close();
    }

    /**
     * Bean的生命周期过程(5步)
     */
    private static void extractedBeanLifeCycle5() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("lifecycle5.xml");
        LifeCycleBean5 lifecycle = context.getBean("lifecycle", LifeCycleBean5.class);
        System.out.println("4 step -> use lifecycle obj" + lifecycle.toString());

        // 手动调用Bean的销毁，调用：LifeCycleBean5#destroyMethod()
        context.close();
    }

    /**
     * FactoryBean的创建
     */
    private static void extractedFactoryBean() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("mybean.xml");
        Book bookstu = context.getBean("mybean", Book.class);
        Book bookstu2 = context.getBean("mybean", Book.class);
        System.out.println(bookstu);
        System.out.println(bookstu2);
    }

    /**
     * 提供公共集合
     */
    private static void extractedMult3() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("mult3.xml");
        BookStu bookstu = context.getBean("bookstu", BookStu.class);
        System.out.println(bookstu);
    }

    /**
     * 集合使用：自定义对象
     */
    private static void extractedMult2() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("mult2.xml");
        Stu stu = context.getBean("stu", Stu.class);
        System.out.println(stu.toString());
    }

    /**
     * 集合使用：基本类型数据
     */
    private static void extractedMult() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("mult.xml");
        Stu stu = context.getBean("stu", Stu.class);
        System.out.println(stu.toString());
    }

    /**
     * 内部Bean注入
     */
    private static void extractedDeptEmp() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dept_emp.xml");
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);
    }

    /**
     * 外部bean注入
     */
    private static void extractedUserDao() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("properties.xml");
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService);
        userService.add();
    }

    /**
     * 通过xml方式创建对象，并且为该对象注入属性，并调用方法
     */
    private static void extractedBook() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean_demo.xml");
        Book book = context.getBean("book", Book.class);
        System.out.println(book);
        book.showMeteData();
        book.showAddress();
        book.showOut();
        book.showOut2();
    }

    /**
     * 测试通过xml创建对象，仅创建最简单的对象和调用方法
     */
    private static void extractedUser() {
        // 加载配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean_demo.xml");
        // 获取配置创建的对象
        User user = context.getBean("user", User.class);
        System.out.println(user);
        user.add();
    }
}
