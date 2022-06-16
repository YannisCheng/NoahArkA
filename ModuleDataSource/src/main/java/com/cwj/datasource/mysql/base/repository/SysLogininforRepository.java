package com.cwj.datasource.mysql.base.repository;

import com.cwj.datasource.mysql.base.entity.SysLogininfor;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 系统访问记录(SysLogininfor)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysLogininforRepository extends JpaRepository<SysLogininfor, Long> {


}