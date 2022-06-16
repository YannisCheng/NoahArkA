package com.cwj.datasource.mysql.area.repository;


import com.cwj.datasource.mysql.area.entity.CityTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 城市表(CityTable)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:44:59
 */
public interface CityTableRepository extends JpaRepository<CityTable, Integer> {


}