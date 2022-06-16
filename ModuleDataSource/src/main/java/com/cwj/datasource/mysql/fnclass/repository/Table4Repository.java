package com.cwj.datasource.mysql.fnclass.repository;

import com.cwj.datasource.mysql.fnclass.entity.Table4;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * 行政5班级表(Table4)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:39:47
 */
public interface Table4Repository extends JpaRepository<Table4, Integer> {

    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCardStu 身份证号
     * @return 实例对象
     */
    Optional<Table4> findByIdcardStu(String idCardStu);

    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param StuId 学号
     * @return 实例对象
     */
    Optional<Table4> findByStuId(String StuId);


}