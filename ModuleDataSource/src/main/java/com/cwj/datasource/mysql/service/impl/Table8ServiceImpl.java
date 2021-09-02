package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.Table8;
import com.cwj.datasource.mysql.dao.Table8Dao;
import com.cwj.datasource.mysql.service.Table8Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Table8)表服务实现类
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
@Service("table8Service")
public class Table8ServiceImpl implements Table8Service {
    @Resource
    private Table8Dao table8Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    @Override
    public Table8 queryById(String stuId) {
        return this.table8Dao.queryById(stuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Table8> queryAllByLimit(int offset, int limit) {
        return this.table8Dao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param table8 实例对象
     * @return 实例对象
     */
    @Override
    public Table8 insert(Table8 table8) {
        this.table8Dao.insert(table8);
        return table8;
    }

    /**
     * 修改数据
     *
     * @param table8 实例对象
     * @return 实例对象
     */
    @Override
    public Table8 update(Table8 table8) {
        this.table8Dao.update(table8);
        return this.queryById(table8.getStuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String stuId) {
        return this.table8Dao.deleteById(stuId) > 0;
    }
}