package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.Table1;
import com.cwj.datasource.mysql.dao.Table1Dao;
import com.cwj.datasource.mysql.service.Table1Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Table1)表服务实现类
 *
 * @author makejava
 * @since 2021-09-02 21:21:12
 */
@Service("table1Service")
public class Table1ServiceImpl implements Table1Service {
    @Resource
    private Table1Dao table1Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    @Override
    public Table1 queryById(String stuId) {
        return this.table1Dao.queryById(stuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Table1> queryAllByLimit(int offset, int limit) {
        return this.table1Dao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param table1 实例对象
     * @return 实例对象
     */
    @Override
    public Table1 insert(Table1 table1) {
        this.table1Dao.insert(table1);
        return table1;
    }

    /**
     * 修改数据
     *
     * @param table1 实例对象
     * @return 实例对象
     */
    @Override
    public Table1 update(Table1 table1) {
        this.table1Dao.update(table1);
        return this.queryById(table1.getStuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String stuId) {
        return this.table1Dao.deleteById(stuId) > 0;
    }
}