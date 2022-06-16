package com.cwj.datasource.mysql.fnclass.repository;

import com.cwj.datasource.mysql.fnclass.entity.Table6;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * 行政7班级表(Table6)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:37:54
 */
public interface Table6Repository extends JpaRepository<Table6, Integer> {

    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCardStu 身份证号
     * @return 实例对象
     */
    Optional<Table6> findByIdcardStu(String idCardStu);

    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param StuId 学号
     * @return 实例对象
     */
    Optional<Table6> findByStuId(String StuId);


}