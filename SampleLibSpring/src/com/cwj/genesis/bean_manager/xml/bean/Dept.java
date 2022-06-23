package com.cwj.genesis.bean_manager.xml.bean;

/**
 * com.cwj.genesis.bean_manager.xml.bean
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-05-25 18:21
 */
public class Dept {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "name='" + name + '\'' +
                '}';
    }
}
