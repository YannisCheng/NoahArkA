package com.cwj.genesis.bean_manager.xml.bean;

import java.util.List;

/**
 * com.cwj.genesis.bean_manager.xml.bean
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-05-25 19:04
 */
public class BookStu {
    private List<String> list;

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "BookStu{" +
                "list=" + list +
                '}';
    }
}
