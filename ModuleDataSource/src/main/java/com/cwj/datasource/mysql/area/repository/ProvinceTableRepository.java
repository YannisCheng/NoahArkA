package com.cwj.datasource.mysql.area.repository;


import com.cwj.datasource.mysql.area.entity.ProvinceTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 省份表(ProvinceTable)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:45:03
 */
public interface ProvinceTableRepository extends JpaRepository<ProvinceTable, Integer> {


}