package com.cwj.datasource.mysql.fnclass.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "table_all")
@ApiModel(value = "TableAll", description = "所有行政班级表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "table_all", comment = "所有行政班级表")
/**
 * 所有行政班级表(TableAll)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:42:32
 */
public class TableAll implements Serializable {

    private static final long serialVersionUID = 945869096625211295L;


    @Column(name = "id")
    @ApiModelProperty("主键id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "stu_id")
    @ApiModelProperty("学号")
    public String stuId;

    @Column(name = "idcard_stu")
    @ApiModelProperty("省份证号")
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
    @ApiModelProperty("身份证出生日期")
    public String date;

    @Column(name = "height")
    @ApiModelProperty("身高（cm）")
    public String height;

    @Column(name = "weight")
    @ApiModelProperty("体重（kg）")
    public String weight;

    @Column(name = "class_id")
    @ApiModelProperty("所在班级的id")
    public String classId;

    @Column(name = "address")
    @ApiModelProperty("身份证显示地址")
    public String address;

}