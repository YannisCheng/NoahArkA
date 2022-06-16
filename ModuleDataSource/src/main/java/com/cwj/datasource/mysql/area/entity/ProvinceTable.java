package com.cwj.datasource.mysql.area.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "province_table")
@ApiModel(value = "ProvinceTable", description = "省份表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "province_table", comment = "省份表")
/**
 * 省份表(ProvinceTable)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:45:03
 */
public class ProvinceTable implements Serializable {

    private static final long serialVersionUID = 426516879923240517L;


    @Column(name = "id")
    @ApiModelProperty("主键id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "province_name")
    @ApiModelProperty("省份名称")
    public String provinceName;

    @Column(name = "province_code")
    @ApiModelProperty("省份代码")
    public String provinceCode;

    @Column(name = "grade")
    @ApiModelProperty("所属层级")
    public String grade;

}