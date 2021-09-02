package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.Table6;
import com.cwj.datasource.mysql.dao.Table6Dao;
import com.cwj.datasource.mysql.service.Table6Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Table6)表服务实现类
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
@Service("table6Service")
public class Table6ServiceImpl implements Table6Service {
    @Resource
    private Table6Dao table6Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    @Override
    public Table6 queryById(String stuId) {
        return this.table6Dao.queryById(stuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Table6> queryAllByLimit(int offset, int limit) {
        return this.table6Dao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param table6 实例对象
     * @return 实例对象
     */
    @Override
    public Table6 insert(Table6 table6) {
        this.table6Dao.insert(table6);
        return table6;
    }

    /**
     * 修改数据
     *
     * @param table6 实例对象
     * @return 实例对象
     */
    @Override
    public Table6 update(Table6 table6) {
        this.table6Dao.update(table6);
        return this.queryById(table6.getStuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String stuId) {
        return this.table6Dao.deleteById(stuId) > 0;
    }
}