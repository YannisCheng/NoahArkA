package com.cwj.datasource.mysql.base.repository;

import com.cwj.datasource.mysql.base.entity.SysOperLog;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 操作日志记录(SysOperLog)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysOperLogRepository extends JpaRepository<SysOperLog, Long> {


}