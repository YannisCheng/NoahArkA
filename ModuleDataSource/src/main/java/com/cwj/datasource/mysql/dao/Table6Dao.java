package com.cwj.datasource.mysql.dao;

import com.cwj.datasource.mysql.entity.Table6;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Table6)表数据库访问层
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
public interface Table6Dao {

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    Table6 queryById(String stuId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Table6> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param table6 实例对象
     * @return 对象列表
     */
    List<Table6> queryAll(Table6 table6);

    /**
     * 新增数据
     *
     * @param table6 实例对象
     * @return 影响行数
     */
    int insert(Table6 table6);

    /**
     * 修改数据
     *
     * @param table6 实例对象
     * @return 影响行数
     */
    int update(Table6 table6);

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 影响行数
     */
    int deleteById(String stuId);

}