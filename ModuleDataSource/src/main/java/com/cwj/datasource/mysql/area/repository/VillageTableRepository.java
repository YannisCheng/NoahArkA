package com.cwj.datasource.mysql.area.repository;


import com.cwj.datasource.mysql.area.entity.VillageTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 街道、社区、村表(VillageTable)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:45:03
 */
public interface VillageTableRepository extends JpaRepository<VillageTable, Integer> {


}