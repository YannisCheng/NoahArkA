package com.cwj.datasource.mysql.dao;

import com.cwj.datasource.mysql.entity.Table3;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Table3)表数据库访问层
 *
 * @author makejava
 * @since 2021-09-02 21:26:43
 */
public interface Table3Dao {

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    Table3 queryById(String stuId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Table3> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param table3 实例对象
     * @return 对象列表
     */
    List<Table3> queryAll(Table3 table3);

    /**
     * 新增数据
     *
     * @param table3 实例对象
     * @return 影响行数
     */
    int insert(Table3 table3);

    /**
     * 修改数据
     *
     * @param table3 实例对象
     * @return 影响行数
     */
    int update(Table3 table3);

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 影响行数
     */
    int deleteById(String stuId);

}