package com.cwj.genesis.bean_manager.xml.bean;

/**
 * com.cwj.genesis.bean_manager.xml.bean
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-05-25 18:21
 */
public class Emp {
    private String name;
    private String genter;
    // 员工所在部门
    private Dept dept;

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenter(String genter) {
        this.genter = genter;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                ", genter='" + genter + '\'' +
                ", dept=" + dept +
                '}';
    }
}
