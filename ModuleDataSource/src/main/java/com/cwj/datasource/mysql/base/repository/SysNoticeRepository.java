package com.cwj.datasource.mysql.base.repository;

import com.cwj.datasource.mysql.base.entity.SysNotice;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 通知公告表(SysNotice)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysNoticeRepository extends JpaRepository<SysNotice, Integer> {


}