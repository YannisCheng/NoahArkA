package com.cwj.datasource.mysql.dao;

import com.cwj.datasource.mysql.entity.Table9;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Table9)表数据库访问层
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
public interface Table9Dao {

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    Table9 queryById(String stuId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Table9> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param table9 实例对象
     * @return 对象列表
     */
    List<Table9> queryAll(Table9 table9);

    /**
     * 新增数据
     *
     * @param table9 实例对象
     * @return 影响行数
     */
    int insert(Table9 table9);

    /**
     * 修改数据
     *
     * @param table9 实例对象
     * @return 影响行数
     */
    int update(Table9 table9);

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 影响行数
     */
    int deleteById(String stuId);

}