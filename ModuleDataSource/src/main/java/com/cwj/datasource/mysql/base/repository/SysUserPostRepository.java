package com.cwj.datasource.mysql.base.repository;

import com.cwj.datasource.mysql.base.entity.SysUserPost;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 用户与岗位关联表(SysUserPost)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysUserPostRepository extends JpaRepository<SysUserPost, Integer> {


}