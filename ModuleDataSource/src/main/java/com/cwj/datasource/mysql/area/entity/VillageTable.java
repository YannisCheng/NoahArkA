package com.cwj.datasource.mysql.area.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "village_table")
@ApiModel(value = "VillageTable", description = "街道、社区、村表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "village_table", comment = "街道、社区、村表")
/**
 * 街道、社区、村表(VillageTable)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:45:03
 */
public class VillageTable implements Serializable {

    private static final long serialVersionUID = 616399417005725031L;


    @Column(name = "id")
    @ApiModelProperty("主键id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "village_name")
    @ApiModelProperty("街道、社区、村名称")
    public String villageName;

    @Column(name = "village_code")
    @ApiModelProperty("街道、社区、村代码")
    public String villageCode;

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