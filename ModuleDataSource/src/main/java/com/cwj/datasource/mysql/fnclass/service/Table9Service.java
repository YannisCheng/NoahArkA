package com.cwj.datasource.mysql.fnclass.service;

import com.cwj.datasource.mysql.fnclass.entity.Table9;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * 行政10班级表(Table9)表服务接口
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:41:55
 */
public interface Table9Service {


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Optional<Table9> findById(Integer id);


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    List<Table9> findAll(Sort sort);


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    Page<Table9> findByPage(Pageable pageable);

    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCard 身份证号
     * @return 实例对象
     */
    Optional<Table9> findByIdCard(String idCard);

    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param stuId 学号
     * @return 实例对象
     */
    Optional<Table9> findByStuId(String stuId);


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    Table9 saveOne(Table9 table9);


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    Table9 updateByObj(Table9 table9);


    // --------------------------------------  删  --------------------------------------

    /**
     * 通过主键id删除单条数据
     *
     * @param id 主键
     */
    void deleteById(Integer id);
}