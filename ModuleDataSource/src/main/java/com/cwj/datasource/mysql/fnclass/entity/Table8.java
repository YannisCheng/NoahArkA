package com.cwj.datasource.mysql.fnclass.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "table_8")
@ApiModel(value = "Table8", description = "行政9班级表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "table_8", comment = "行政9班级表")
/**
 * 行政9班级表(Table8)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:41:13
 */
public class Table8 implements Serializable {

    private static final long serialVersionUID = 940727556759912673L;


    @Column(name = "id")
    @ApiModelProperty("主键id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "stu_id")
    @ApiModelProperty("学号")
    public String stuId;

    @Column(name = "idcard_stu")
    @ApiModelProperty("身份证号码")
    public String idcardStu;

    @Column(name = "name_stu")
    @ApiModelProperty("姓名")
    public String nameStu;

    @Column(name = "nation_stu")
    @ApiModelProperty("民族")
    public String nationStu;

    @Column(name = "sex_stu")
    @ApiModelProperty("性别")
    public String sexStu;

    @Column(name = "date")
    @ApiModelProperty("出生日期-身份证")
    public String date;

    @Column(name = "height")
    @ApiModelProperty("身高（cm）")
    public String height;

    @Column(name = "weight")
    @ApiModelProperty("体重（kg）")
    public String weight;

    @Column(name = "xiongwei")
    @ApiModelProperty("胸围（cm）")
    public String xiongwei;

    @Column(name = "address")
    @ApiModelProperty("身份证所在地址")
    public String address;

}