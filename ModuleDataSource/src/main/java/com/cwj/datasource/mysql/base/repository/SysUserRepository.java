package com.cwj.datasource.mysql.base.repository;

import com.cwj.datasource.mysql.base.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 用户信息表(SysUser)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {


}