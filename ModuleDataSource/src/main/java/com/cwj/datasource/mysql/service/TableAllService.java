package com.cwj.datasource.mysql.service;

import com.cwj.datasource.mysql.entity.TableAll;
import java.util.List;

/**
 * (TableAll)表服务接口
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
public interface TableAllService {

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    TableAll queryById(String stuId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TableAll> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tableAll 实例对象
     * @return 实例对象
     */
    TableAll insert(TableAll tableAll);

    /**
     * 修改数据
     *
     * @param tableAll 实例对象
     * @return 实例对象
     */
    TableAll update(TableAll tableAll);

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    boolean deleteById(String stuId);

}