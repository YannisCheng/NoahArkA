package com.cwj.datasource.mysql.service;

import com.cwj.datasource.mysql.entity.CountryTable;

/**
 * com.cwj.datasource.mysql.service
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-02 17:20
 */
public interface CountyTableService {

    CountryTable queryById(String countryCode);
}
