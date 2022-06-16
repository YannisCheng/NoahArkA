package com.cwj.datasource.mysql.area.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "city_table")
@ApiModel(value = "CityTable", description = "城市表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "city_table", comment = "城市表")
/**
 * 城市表(CityTable)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:44:56
 */
public class CityTable implements Serializable {

    private static final long serialVersionUID = 906533794712796511L;


    @Column(name = "id")
    @ApiModelProperty("城市id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "city_name")
    @ApiModelProperty("城市名称")
    public String cityName;

    @Column(name = "city_code")
    @ApiModelProperty("城市编码")
    public String cityCode;

    @Column(name = "province_code")
    @ApiModelProperty("所属省份编码")
    public String provinceCode;

    @Column(name = "grade")
    @ApiModelProperty("所在行政区划层级")
    public String grade;

}