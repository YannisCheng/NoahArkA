package com.cwj.genesis.xml.bean;

/**
 * com.cwj.genesis.xml.bean
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-05-25 18:21
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
