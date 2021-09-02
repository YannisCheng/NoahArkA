package com.cwj.datasource.mysql.service;

import com.cwj.datasource.mysql.entity.Table10;
import java.util.List;

/**
 * (Table10)表服务接口
 *
 * @author makejava
 * @since 2021-09-02 21:24:40
 */
public interface Table10Service {

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    Table10 queryById(String stuId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Table10> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param table10 实例对象
     * @return 实例对象
     */
    Table10 insert(Table10 table10);

    /**
     * 修改数据
     *
     * @param table10 实例对象
     * @return 实例对象
     */
    Table10 update(Table10 table10);

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    boolean deleteById(String stuId);

}