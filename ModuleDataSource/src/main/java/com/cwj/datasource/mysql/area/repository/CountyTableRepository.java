package com.cwj.datasource.mysql.area.repository;

import com.cwj.datasource.mysql.area.entity.CountyTable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 区县表(CountyTable)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:45:03
 */
public interface CountyTableRepository extends JpaRepository<CountyTable, Integer> {


}