package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.Table0;
import com.cwj.datasource.mysql.dao.Table0Dao;
import com.cwj.datasource.mysql.service.Table0Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Table0)表服务实现类
 *
 * @author makejava
 * @since 2021-09-02 21:16:31
 */
@Service("table0Service")
public class Table0ServiceImpl implements Table0Service {
    @Resource
    private Table0Dao table0Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    @Override
    public Table0 queryById(String stuId) {
        return this.table0Dao.queryById(stuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Table0> queryAllByLimit(int offset, int limit) {
        return this.table0Dao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param table0 实例对象
     * @return 实例对象
     */
    @Override
    public Table0 insert(Table0 table0) {
        this.table0Dao.insert(table0);
        return table0;
    }

    /**
     * 修改数据
     *
     * @param table0 实例对象
     * @return 实例对象
     */
    @Override
    public Table0 update(Table0 table0) {
        this.table0Dao.update(table0);
        return this.queryById(table0.getStuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String stuId) {
        return this.table0Dao.deleteById(stuId) > 0;
    }
}