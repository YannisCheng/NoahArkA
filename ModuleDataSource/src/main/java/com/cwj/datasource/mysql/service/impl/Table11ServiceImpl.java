package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.Table11;
import com.cwj.datasource.mysql.dao.Table11Dao;
import com.cwj.datasource.mysql.service.Table11Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Table11)表服务实现类
 *
 * @author makejava
 * @since 2021-09-02 21:25:26
 */
@Service("table11Service")
public class Table11ServiceImpl implements Table11Service {
    @Resource
    private Table11Dao table11Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    @Override
    public Table11 queryById(String stuId) {
        return this.table11Dao.queryById(stuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Table11> queryAllByLimit(int offset, int limit) {
        return this.table11Dao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param table11 实例对象
     * @return 实例对象
     */
    @Override
    public Table11 insert(Table11 table11) {
        this.table11Dao.insert(table11);
        return table11;
    }

    /**
     * 修改数据
     *
     * @param table11 实例对象
     * @return 实例对象
     */
    @Override
    public Table11 update(Table11 table11) {
        this.table11Dao.update(table11);
        return this.queryById(table11.getStuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String stuId) {
        return this.table11Dao.deleteById(stuId) > 0;
    }
}