package com.cwj.datasource.mysql.base.repository;

import com.cwj.datasource.mysql.base.entity.SysJobLog;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 定时任务调度日志表(SysJobLog)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysJobLogRepository extends JpaRepository<SysJobLog, Long> {


}