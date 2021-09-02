package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.Table2;
import com.cwj.datasource.mysql.dao.Table2Dao;
import com.cwj.datasource.mysql.service.Table2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Table2)表服务实现类
 *
 * @author makejava
 * @since 2021-09-02 21:26:08
 */
@Service("table2Service")
public class Table2ServiceImpl implements Table2Service {
    @Resource
    private Table2Dao table2Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    @Override
    public Table2 queryById(String stuId) {
        return this.table2Dao.queryById(stuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Table2> queryAllByLimit(int offset, int limit) {
        return this.table2Dao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param table2 实例对象
     * @return 实例对象
     */
    @Override
    public Table2 insert(Table2 table2) {
        this.table2Dao.insert(table2);
        return table2;
    }

    /**
     * 修改数据
     *
     * @param table2 实例对象
     * @return 实例对象
     */
    @Override
    public Table2 update(Table2 table2) {
        this.table2Dao.update(table2);
        return this.queryById(table2.getStuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String stuId) {
        return this.table2Dao.deleteById(stuId) > 0;
    }
}