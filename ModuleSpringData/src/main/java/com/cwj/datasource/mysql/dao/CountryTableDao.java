package com.cwj.datasource.mysql.dao;

import com.cwj.datasource.mysql.entity.CountryTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * com.cwj.datasource.mysql.dao
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-02 16:56
 */
public interface CountryTableDao {

    CountryTable queryById(String countryCode);

    List<CountryTable> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    int insert(CountryTable countryTable);

    int update(CountryTable countryTable);

    int deleteById(CountryTable countryTable);
}
