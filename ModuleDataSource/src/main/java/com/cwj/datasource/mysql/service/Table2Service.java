package com.cwj.datasource.mysql.service;

import com.cwj.datasource.mysql.entity.Table2;
import java.util.List;

/**
 * (Table2)表服务接口
 *
 * @author makejava
 * @since 2021-09-02 21:26:08
 */
public interface Table2Service {

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    Table2 queryById(String stuId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Table2> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param table2 实例对象
     * @return 实例对象
     */
    Table2 insert(Table2 table2);

    /**
     * 修改数据
     *
     * @param table2 实例对象
     * @return 实例对象
     */
    Table2 update(Table2 table2);

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    boolean deleteById(String stuId);

}