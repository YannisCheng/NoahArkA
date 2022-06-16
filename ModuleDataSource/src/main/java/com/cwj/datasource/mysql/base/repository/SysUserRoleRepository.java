package com.cwj.datasource.mysql.base.repository;

import com.cwj.datasource.mysql.base.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 用户和角色关联表(SysUserRole)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysUserRoleRepository extends JpaRepository<SysUserRole, Integer> {


}