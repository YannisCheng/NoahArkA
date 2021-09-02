package com.cwj.datasource.mysql.service;

import com.cwj.datasource.mysql.entity.Table11;
import java.util.List;

/**
 * (Table11)表服务接口
 *
 * @author makejava
 * @since 2021-09-02 21:25:26
 */
public interface Table11Service {

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    Table11 queryById(String stuId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Table11> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param table11 实例对象
     * @return 实例对象
     */
    Table11 insert(Table11 table11);

    /**
     * 修改数据
     *
     * @param table11 实例对象
     * @return 实例对象
     */
    Table11 update(Table11 table11);

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    boolean deleteById(String stuId);

}