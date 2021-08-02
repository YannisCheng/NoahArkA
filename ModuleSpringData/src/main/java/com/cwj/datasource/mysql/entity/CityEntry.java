package com.cwj.datasource.mysql.entity;

import lombok.Data;

/**
 * com.cwj.datasource.mysql.entity
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-07-31 18:10
 */
@Data

public class CityEntry {
    private int id;
    private String cityName;
    private String cityCode;
    private String provinceCode;
    private String grade;
}
