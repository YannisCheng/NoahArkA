package com.cwj.datasource.mysql.dao;

import com.cwj.datasource.mysql.entity.CountyTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (CountyTable)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-02 20:38:19
 */
public interface CountyTableDao {

    /**
     * 通过ID查询单条数据
     *
     * @param countyCode 主键
     * @return 实例对象
     */
    CountyTable queryById(String countyCode);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CountyTable> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param countyTable 实例对象
     * @return 对象列表
     */
    List<CountyTable> queryAll(CountyTable countyTable);

    /**
     * 新增数据
     *
     * @param countyTable 实例对象
     * @return 影响行数
     */
    int insert(CountyTable countyTable);

    /**
     * 修改数据
     *
     * @param countyTable 实例对象
     * @return 影响行数
     */
    int update(CountyTable countyTable);

    /**
     * 通过主键删除数据
     *
     * @param countyCode 主键
     * @return 影响行数
     */
    int deleteById(String countyCode);

}