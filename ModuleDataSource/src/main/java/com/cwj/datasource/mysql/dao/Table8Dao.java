package com.cwj.datasource.mysql.dao;

import com.cwj.datasource.mysql.entity.Table8;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Table8)表数据库访问层
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
public interface Table8Dao {

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    Table8 queryById(String stuId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Table8> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param table8 实例对象
     * @return 对象列表
     */
    List<Table8> queryAll(Table8 table8);

    /**
     * 新增数据
     *
     * @param table8 实例对象
     * @return 影响行数
     */
    int insert(Table8 table8);

    /**
     * 修改数据
     *
     * @param table8 实例对象
     * @return 影响行数
     */
    int update(Table8 table8);

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 影响行数
     */
    int deleteById(String stuId);

}