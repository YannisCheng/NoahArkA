package com.cwj.datasource.mysql.fnclass.repository;

import com.cwj.datasource.mysql.fnclass.entity.TableAll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * 所有行政班级表(TableAll)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:42:32
 */
public interface TableAllRepository extends JpaRepository<TableAll, Integer> {

    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCardStu 身份证号
     * @return 实例对象
     */
    Optional<TableAll> findByIdcardStu(String idCardStu);

    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param StuId 学号
     * @return 实例对象
     */
    Optional<TableAll> findByStuId(String StuId);


}