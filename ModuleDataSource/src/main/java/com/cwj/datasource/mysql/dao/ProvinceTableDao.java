package com.cwj.datasource.mysql.dao;

import com.cwj.datasource.mysql.entity.ProvinceTable;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ProvinceTable)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-02 20:53:07
 */
public interface ProvinceTableDao {

    /**
     * 通过ID查询单条数据
     *
     * @param provinceCode 主键
     * @return 实例对象
     */
    ProvinceTable queryById(String provinceCode);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ProvinceTable> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param provinceTable 实例对象
     * @return 对象列表
     */
    List<ProvinceTable> queryAll(ProvinceTable provinceTable);

    /**
     * 新增数据
     *
     * @param provinceTable 实例对象
     * @return 影响行数
     */
    int insert(ProvinceTable provinceTable);

    /**
     * 修改数据
     *
     * @param provinceTable 实例对象
     * @return 影响行数
     */
    int update(ProvinceTable provinceTable);

    /**
     * 通过主键删除数据
     *
     * @param provinceCode 主键
     * @return 影响行数
     */
    int deleteById(String provinceCode);

}