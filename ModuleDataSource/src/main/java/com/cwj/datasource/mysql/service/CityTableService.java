package com.cwj.datasource.mysql.service;

import com.cwj.datasource.mysql.entity.CityTable;
import java.util.List;

/**
 * (CityTable)表服务接口
 *
 * @author makejava
 * @since 2021-08-02 11:04:46
 */
public interface CityTableService {

    /**
     * 通过ID查询单条数据
     *
     * @param cityCode 主键
     * @return 实例对象
     */
    CityTable queryById(String cityCode);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CityTable> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param cityTable 实例对象
     * @return 实例对象
     */
    CityTable insert(CityTable cityTable);

    /**
     * 修改数据
     *
     * @param cityTable 实例对象
     * @return 实例对象
     */
    CityTable update(CityTable cityTable);

    /**
     * 通过主键删除数据
     *
     * @param cityCode 主键
     * @return 是否成功
     */
    boolean deleteById(String cityCode);

}