package com.cwj.datasource.mysql.base.repository;

import com.cwj.datasource.mysql.base.entity.SysRoleDept;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 角色和部门关联表(SysRoleDept)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysRoleDeptRepository extends JpaRepository<SysRoleDept, Integer> {


}