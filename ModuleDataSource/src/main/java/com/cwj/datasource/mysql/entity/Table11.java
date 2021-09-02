package com.cwj.datasource.mysql.entity;

import java.io.Serializable;

/**
 * (Table11)实体类
 *
 * @author makejava
 * @since 2021-09-02 21:25:26
 */
public class Table11 implements Serializable {
    private static final long serialVersionUID = -29051936548791952L;
    
    private Integer id;
    
    private String stuId;
    
    private String idcardStu;
    
    private String nameStu;
    
    private String nationStu;
    
    private String sexStu;
    
    private String date;
    
    private String height;
    
    private String weight;
    
    private String xiongwei;
    
    private String address;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getIdcardStu() {
        return idcardStu;
    }

    public void setIdcardStu(String idcardStu) {
        this.idcardStu = idcardStu;
    }

    public String getNameStu() {
        return nameStu;
    }

    public void setNameStu(String nameStu) {
        this.nameStu = nameStu;
    }

    public String getNationStu() {
        return nationStu;
    }

    public void setNationStu(String nationStu) {
        this.nationStu = nationStu;
    }

    public String getSexStu() {
        return sexStu;
    }

    public void setSexStu(String sexStu) {
        this.sexStu = sexStu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getXiongwei() {
        return xiongwei;
    }

    public void setXiongwei(String xiongwei) {
        this.xiongwei = xiongwei;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}