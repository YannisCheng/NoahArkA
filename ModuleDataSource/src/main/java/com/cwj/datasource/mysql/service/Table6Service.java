package com.cwj.datasource.mysql.service;

import com.cwj.datasource.mysql.entity.Table6;
import java.util.List;

/**
 * (Table6)表服务接口
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
public interface Table6Service {

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    Table6 queryById(String stuId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Table6> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param table6 实例对象
     * @return 实例对象
     */
    Table6 insert(Table6 table6);

    /**
     * 修改数据
     *
     * @param table6 实例对象
     * @return 实例对象
     */
    Table6 update(Table6 table6);

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    boolean deleteById(String stuId);

}