package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.Table10;
import com.cwj.datasource.mysql.dao.Table10Dao;
import com.cwj.datasource.mysql.service.Table10Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Table10)表服务实现类
 *
 * @author makejava
 * @since 2021-09-02 21:24:40
 */
@Service("table10Service")
public class Table10ServiceImpl implements Table10Service {
    @Resource
    private Table10Dao table10Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    @Override
    public Table10 queryById(String stuId) {
        return this.table10Dao.queryById(stuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Table10> queryAllByLimit(int offset, int limit) {
        return this.table10Dao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param table10 实例对象
     * @return 实例对象
     */
    @Override
    public Table10 insert(Table10 table10) {
        this.table10Dao.insert(table10);
        return table10;
    }

    /**
     * 修改数据
     *
     * @param table10 实例对象
     * @return 实例对象
     */
    @Override
    public Table10 update(Table10 table10) {
        this.table10Dao.update(table10);
        return this.queryById(table10.getStuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String stuId) {
        return this.table10Dao.deleteById(stuId) > 0;
    }
}