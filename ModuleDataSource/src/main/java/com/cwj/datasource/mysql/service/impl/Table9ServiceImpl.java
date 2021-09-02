package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.Table9;
import com.cwj.datasource.mysql.dao.Table9Dao;
import com.cwj.datasource.mysql.service.Table9Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Table9)表服务实现类
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
@Service("table9Service")
public class Table9ServiceImpl implements Table9Service {
    @Resource
    private Table9Dao table9Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    @Override
    public Table9 queryById(String stuId) {
        return this.table9Dao.queryById(stuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Table9> queryAllByLimit(int offset, int limit) {
        return this.table9Dao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param table9 实例对象
     * @return 实例对象
     */
    @Override
    public Table9 insert(Table9 table9) {
        this.table9Dao.insert(table9);
        return table9;
    }

    /**
     * 修改数据
     *
     * @param table9 实例对象
     * @return 实例对象
     */
    @Override
    public Table9 update(Table9 table9) {
        this.table9Dao.update(table9);
        return this.queryById(table9.getStuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String stuId) {
        return this.table9Dao.deleteById(stuId) > 0;
    }
}