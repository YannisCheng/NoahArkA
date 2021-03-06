package com.cwj.genesis.bean_manager.xml.factory_bean;

import com.cwj.genesis.bean_manager.xml.declare.Book;
import org.springframework.beans.factory.FactoryBean;

/**
 * com.cwj.genesis.bean_manager.xml.factory_bean
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-05-25 19:35
 */
public class MyFactoryBean implements FactoryBean<Book> {

    /**
     * 返回指定的Bean类型
     */
    @Override
    public Book getObject() throws Exception {
        return new Book("Jinan");
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }

}
