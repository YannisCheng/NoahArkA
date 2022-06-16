package com.cwj.datasource.mysql.area.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "town_table")
@ApiModel(value = "TownTable", description = "乡镇表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "town_table", comment = "乡镇表")
/**
 * 乡镇表(TownTable)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:45:03
 */
public class TownTable implements Serializable {

    private static final long serialVersionUID = 372541535519522643L;


    @Column(name = "id")
    @ApiModelProperty("主键id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "town_name")
    @ApiModelProperty("乡镇名称")
    public String townName;

    @Column(name = "town_code")
    @ApiModelProperty("乡镇代码")
    public String townCode;

    @Column(name = "county_code")
    @ApiModelProperty("区县代码")
    public String countyCode;

    @Column(name = "city_code")
    @ApiModelProperty("城市代码")
    public String cityCode;

    @Column(name = "province_code")
    @ApiModelProperty("省份代码")
    public String provinceCode;

    @Column(name = "grade")
    @ApiModelProperty("所属层级")
    public String grade;

}