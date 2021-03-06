package com.cwj.datasource.mysql.base.repository;

import com.cwj.datasource.mysql.base.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 角色信息表(SysRole)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysRoleRepository extends JpaRepository<SysRole, Long> {


}