package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.dao.CountryTableDao;
import com.cwj.datasource.mysql.entity.CountryTable;
import com.cwj.datasource.mysql.service.CountyTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * com.cwj.datasource.mysql.service.impl
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-02 17:21
 */
@Service("countyTableServiceA")
public class CountyTableServiceImpl implements CountyTableService {

    @Resource
    private CountryTableDao countryTableDao;

    @Override
    public CountryTable queryById(String countryCode) {
        return countryTableDao.queryById(countryCode);
    }
}
