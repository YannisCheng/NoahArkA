package com.cwj.common.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring工具类：方便在非spring管理环境中获取bean。
 *
 * 当一个类实现了这个接口之后，这个类就可以方便的获得ApplicationContext对象（spring上下文），
 * Spring发现某个Bean实现了ApplicationContextAware接口，Spring容器会在创建该Bean之后，
 * 自动调用该Bean的setApplicationContext（参数）方法，调用该方法时，
 * 会将容器本身ApplicationContext对象作为参数传递给该方法。
 *
 * @author ChengWenjian
 */
@Component
public final class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 获取对象
     *
     * @param name 通过name获取 Bean.
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException e
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 获取类型为requiredType的对象
     *
     * @param clz 通过class获取Bean.
     * @return T
     * @throws BeansException e
     */
    public static <T> T getBean(Class<T> clz) throws BeansException {
        return (T) applicationContext.getBean(clz);
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     *
     * @param name bean name
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     *
     * @param name bean name
     * @return boolean
     * @throws NoSuchBeanDefinitionException e
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    /**
     * @param name bean name
     * @return Class 注册对象的类型
     * @throws NoSuchBeanDefinitionException e
     */
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     *
     * @param name bean name
     * @return 别名
     * @throws NoSuchBeanDefinitionException e
     */
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(name);
    }

    /**
     * <a href="https://blog.csdn.net/qq_29860591/article/details/108728150">获取aop代理对象</a>
     * <a href="https://www.jianshu.com/p/2ab568e7aaf7">场景使用说明</a>
     * 原来在springAOP的用法中，只有代理的类才会被切入，我们在controller层调用service的方法的时候，是可以被切入的。
     * 但是如果我们在service层 A方法中，调用B方法，切点切的是B方法，那么这时候是不会切入的。
     * 解决办法就是在A方法中使用((Service)AopContext.currentProxy()).B() 来调用B方法，这样一来，就能切入了。
     *
     * @param invoker 被代理的类
     * @return 被代理类的对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAopProxy(T invoker) {
        return (T) AopContext.currentProxy();
    }

    /**
     * 获取当前的环境配置，无配置返回null
     *
     * @return 当前的环境配置
     */
    public static String[] getActiveProfiles() {
        return applicationContext.getEnvironment().getActiveProfiles();
    }

    /**
     * 获取当前的环境配置，当有多个环境配置时，只获取第一个
     *
     * @return 当前的环境配置
     */
    public static String getActiveProfile() {
        final String[] activeProfiles = getActiveProfiles();

        return activeProfiles[0];
    }

    /**
     * 重写接口的方法,该方法的参数为框架自动加载的IOC容器对象
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws BeansException e
     */
    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }
}
