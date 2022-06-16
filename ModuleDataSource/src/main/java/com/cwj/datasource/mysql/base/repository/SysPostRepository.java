package com.cwj.datasource.mysql.base.repository;

import com.cwj.datasource.mysql.base.entity.SysPost;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 岗位信息表(SysPost)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysPostRepository extends JpaRepository<SysPost, Long> {


}