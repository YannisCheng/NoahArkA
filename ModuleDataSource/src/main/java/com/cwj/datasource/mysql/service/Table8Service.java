package com.cwj.datasource.mysql.service;

import com.cwj.datasource.mysql.entity.Table8;
import java.util.List;

/**
 * (Table8)表服务接口
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
public interface Table8Service {

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    Table8 queryById(String stuId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Table8> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param table8 实例对象
     * @return 实例对象
     */
    Table8 insert(Table8 table8);

    /**
     * 修改数据
     *
     * @param table8 实例对象
     * @return 实例对象
     */
    Table8 update(Table8 table8);

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    boolean deleteById(String stuId);

}