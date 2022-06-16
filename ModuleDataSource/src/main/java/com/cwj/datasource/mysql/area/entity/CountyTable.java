package com.cwj.datasource.mysql.area.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "county_table")
@ApiModel(value = "CountyTable", description = "区县表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "county_table", comment = "区县表")
/**
 * 区县表(CountyTable)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:45:03
 */
public class CountyTable implements Serializable {

    private static final long serialVersionUID = 172150830906058362L;


    @Column(name = "id")
    @ApiModelProperty("主键id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "county_name")
    @ApiModelProperty("区县名称")
    public String countyName;

    @Column(name = "county_code")
    @ApiModelProperty("区县代码")
    public String countyCode;

    @Column(name = "city_code")
    @ApiModelProperty("所属城市代码")
    public String cityCode;

    @Column(name = "province_code")
    @ApiModelProperty("所属省份代码")
    public String provinceCode;

    @Column(name = "grade")
    @ApiModelProperty("所在层级 ")
    public String grade;

}