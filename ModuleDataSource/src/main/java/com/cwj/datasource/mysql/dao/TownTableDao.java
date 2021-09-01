package com.cwj.datasource.mysql.dao;

import com.cwj.datasource.mysql.entity.TownTable;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TownTable)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-02 20:55:23
 */
public interface TownTableDao {

    /**
     * 通过ID查询单条数据
     *
     * @param townCode 主键
     * @return 实例对象
     */
    TownTable queryById(String townCode);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TownTable> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param townTable 实例对象
     * @return 对象列表
     */
    List<TownTable> queryAll(TownTable townTable);

    /**
     * 新增数据
     *
     * @param townTable 实例对象
     * @return 影响行数
     */
    int insert(TownTable townTable);

    /**
     * 修改数据
     *
     * @param townTable 实例对象
     * @return 影响行数
     */
    int update(TownTable townTable);

    /**
     * 通过主键删除数据
     *
     * @param townCode 主键
     * @return 影响行数
     */
    int deleteById(String townCode);

}