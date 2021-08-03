package com.cwj.datasource.mysql.service;

import com.cwj.datasource.mysql.entity.VillageTable;
import java.util.List;

/**
 * (VillageTable)表服务接口
 *
 * @author makejava
 * @since 2021-08-02 21:08:15
 */
public interface VillageTableService {

    /**
     * 通过ID查询单条数据
     *
     * @param villageCode 主键
     * @return 实例对象
     */
    VillageTable queryById(String villageCode);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<VillageTable> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param villageTable 实例对象
     * @return 实例对象
     */
    VillageTable insert(VillageTable villageTable);

    /**
     * 修改数据
     *
     * @param villageTable 实例对象
     * @return 实例对象
     */
    VillageTable update(VillageTable villageTable);

    /**
     * 通过主键删除数据
     *
     * @param villageCode 主键
     * @return 是否成功
     */
    boolean deleteById(String villageCode);

}