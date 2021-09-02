package com.cwj.datasource.mysql.dao;

import com.cwj.datasource.mysql.entity.Table11;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Table11)表数据库访问层
 *
 * @author makejava
 * @since 2021-09-02 21:25:26
 */
public interface Table11Dao {

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    Table11 queryById(String stuId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Table11> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param table11 实例对象
     * @return 对象列表
     */
    List<Table11> queryAll(Table11 table11);

    /**
     * 新增数据
     *
     * @param table11 实例对象
     * @return 影响行数
     */
    int insert(Table11 table11);

    /**
     * 修改数据
     *
     * @param table11 实例对象
     * @return 影响行数
     */
    int update(Table11 table11);

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 影响行数
     */
    int deleteById(String stuId);

}