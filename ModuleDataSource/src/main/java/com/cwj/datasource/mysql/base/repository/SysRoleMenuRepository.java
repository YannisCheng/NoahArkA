package com.cwj.datasource.mysql.base.repository;

import com.cwj.datasource.mysql.base.entity.SysRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 角色和菜单关联表(SysRoleMenu)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysRoleMenuRepository extends JpaRepository<SysRoleMenu, Integer> {


}