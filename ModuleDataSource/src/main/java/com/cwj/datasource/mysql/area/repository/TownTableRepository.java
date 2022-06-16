package com.cwj.datasource.mysql.area.repository;


import com.cwj.datasource.mysql.area.entity.TownTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 乡镇表(TownTable)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:45:03
 */
public interface TownTableRepository extends JpaRepository<TownTable, Integer> {


}