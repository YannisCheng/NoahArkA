package com.cwj.genesis.xml.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * com.cwj.genesis.xml.lifecycle
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-05-25 20:46
 */
public class LifeCycleBean7 implements BeanPostProcessor {

    public LifeCycleBean7() {
        //System.out.println("1 step -> construct");
    }

    private String name;

    public void setName(String name) {
        this.name = name;
        //System.out.println("2 step -> setter");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("AfterInitialization");
        return bean;
    }

    public void initMethod(){
        //System.out.println("4 step -> init method");
    }

    public void destroyMethod(){
        //System.out.println("7 step -> destroy method");
    }

    /*@Override
    public String toString() {
        return "LifeCycleBean7{" +
                "name='" + name + '\'' +
                '}';
    }*/
}
