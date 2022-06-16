package com.cwj.datasource.mysql.base.repository;

import com.cwj.datasource.mysql.base.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 菜单权限表(SysMenu)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysMenuRepository extends JpaRepository<SysMenu, Long> {


}