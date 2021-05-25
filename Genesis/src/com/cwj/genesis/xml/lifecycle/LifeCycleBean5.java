package com.cwj.genesis.xml.lifecycle;

/**
 * com.cwj.genesis.xml.lifecycle
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-05-25 20:20
 */
public class LifeCycleBean5 {

    public LifeCycleBean5() {
        System.out.println("1 step -> construct");
    }

    private String name;

    public void setName(String name) {
        this.name = name;
        System.out.println("2 step -> setter");
    }

    /**
     * Bean对象的初始化方法
     */
    public void initMethod() {
        System.out.println("3 step -> init method");
    }

    /**
     * Bean对象的销毁方法
     */
    public void destroyMethod() {
        System.out.println("5 step -> destroy method");
    }

    @Override
    public String toString() {
        return "LifeCycleBean5{" +
                "name='" + name + '\'' +
                '}';
    }
}
