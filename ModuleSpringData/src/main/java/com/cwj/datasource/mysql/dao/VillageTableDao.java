package com.cwj.datasource.mysql.dao;

import com.cwj.datasource.mysql.entity.VillageTable;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (VillageTable)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-02 21:08:15
 */
public interface VillageTableDao {

    /**
     * 通过ID查询单条数据
     *
     * @param villageCode 主键
     * @return 实例对象
     */
    VillageTable queryById(String villageCode);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<VillageTable> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param villageTable 实例对象
     * @return 对象列表
     */
    List<VillageTable> queryAll(VillageTable villageTable);

    /**
     * 新增数据
     *
     * @param villageTable 实例对象
     * @return 影响行数
     */
    int insert(VillageTable villageTable);

    /**
     * 修改数据
     *
     * @param villageTable 实例对象
     * @return 影响行数
     */
    int update(VillageTable villageTable);

    /**
     * 通过主键删除数据
     *
     * @param villageCode 主键
     * @return 影响行数
     */
    int deleteById(String villageCode);

}