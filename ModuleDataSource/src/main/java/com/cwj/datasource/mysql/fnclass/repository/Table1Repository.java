package com.cwj.datasource.mysql.fnclass.repository;

import com.cwj.datasource.mysql.fnclass.entity.Table1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * 行政2班级表(Table1)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:37:42
 */
public interface Table1Repository extends JpaRepository<Table1, Integer> {

    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCardStu 身份证号
     * @return 实例对象
     */
    Optional<Table1> findByIdcardStu(String idCardStu);

    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param StuId 学号
     * @return 实例对象
     */
    Optional<Table1> findByStuId(String StuId);


}