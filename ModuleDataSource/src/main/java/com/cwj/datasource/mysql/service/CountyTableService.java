package com.cwj.datasource.mysql.service;

import com.cwj.datasource.mysql.entity.CountyTable;
import java.util.List;

/**
 * (CountyTable)表服务接口
 *
 * @author makejava
 * @since 2021-08-02 20:38:19
 */
public interface CountyTableService {

    /**
     * 通过ID查询单条数据
     *
     * @param countyCode 主键
     * @return 实例对象
     */
    CountyTable queryById(String countyCode);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CountyTable> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param countyTable 实例对象
     * @return 实例对象
     */
    CountyTable insert(CountyTable countyTable);

    /**
     * 修改数据
     *
     * @param countyTable 实例对象
     * @return 实例对象
     */
    CountyTable update(CountyTable countyTable);

    /**
     * 通过主键删除数据
     *
     * @param countyCode 主键
     * @return 是否成功
     */
    boolean deleteById(String countyCode);

}