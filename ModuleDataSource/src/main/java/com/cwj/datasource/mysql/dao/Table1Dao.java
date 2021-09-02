package com.cwj.datasource.mysql.dao;

import com.cwj.datasource.mysql.entity.Table1;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Table1)表数据库访问层
 *
 * @author makejava
 * @since 2021-09-02 21:21:10
 */
public interface Table1Dao {

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    Table1 queryById(String stuId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Table1> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param table1 实例对象
     * @return 对象列表
     */
    List<Table1> queryAll(Table1 table1);

    /**
     * 新增数据
     *
     * @param table1 实例对象
     * @return 影响行数
     */
    int insert(Table1 table1);

    /**
     * 修改数据
     *
     * @param table1 实例对象
     * @return 影响行数
     */
    int update(Table1 table1);

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 影响行数
     */
    int deleteById(String stuId);

}