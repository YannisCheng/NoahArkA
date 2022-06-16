package com.cwj.datasource.mysql.base.repository;

import com.cwj.datasource.mysql.base.entity.SysDept;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 部门表(SysDept)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:13
 */
public interface SysDeptRepository extends JpaRepository<SysDept, Long> {


}