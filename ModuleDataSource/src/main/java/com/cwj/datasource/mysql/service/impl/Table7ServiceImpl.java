package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.Table7;
import com.cwj.datasource.mysql.dao.Table7Dao;
import com.cwj.datasource.mysql.service.Table7Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Table7)表服务实现类
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
@Service("table7Service")
public class Table7ServiceImpl implements Table7Service {
    @Resource
    private Table7Dao table7Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    @Override
    public Table7 queryById(String stuId) {
        return this.table7Dao.queryById(stuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Table7> queryAllByLimit(int offset, int limit) {
        return this.table7Dao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param table7 实例对象
     * @return 实例对象
     */
    @Override
    public Table7 insert(Table7 table7) {
        this.table7Dao.insert(table7);
        return table7;
    }

    /**
     * 修改数据
     *
     * @param table7 实例对象
     * @return 实例对象
     */
    @Override
    public Table7 update(Table7 table7) {
        this.table7Dao.update(table7);
        return this.queryById(table7.getStuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String stuId) {
        return this.table7Dao.deleteById(stuId) > 0;
    }
}