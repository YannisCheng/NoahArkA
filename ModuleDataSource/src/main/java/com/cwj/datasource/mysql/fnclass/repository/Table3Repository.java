package com.cwj.datasource.mysql.fnclass.repository;

import com.cwj.datasource.mysql.fnclass.entity.Table3;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * 行政4班级表(Table3)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:38:48
 */
public interface Table3Repository extends JpaRepository<Table3, Integer> {

    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCardStu 身份证号
     * @return 实例对象
     */
    Optional<Table3> findByIdcardStu(String idCardStu);

    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param StuId 学号
     * @return 实例对象
     */
    Optional<Table3> findByStuId(String StuId);


}