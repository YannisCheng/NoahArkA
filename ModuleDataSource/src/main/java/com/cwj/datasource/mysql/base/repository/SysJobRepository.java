package com.cwj.datasource.mysql.base.repository;

import com.cwj.datasource.mysql.base.entity.SysJob;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 定时任务调度表(SysJob)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysJobRepository extends JpaRepository<SysJob, Long> {


}