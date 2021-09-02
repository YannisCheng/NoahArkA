package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.Table5;
import com.cwj.datasource.mysql.dao.Table5Dao;
import com.cwj.datasource.mysql.service.Table5Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Table5)表服务实现类
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
@Service("table5Service")
public class Table5ServiceImpl implements Table5Service {
    @Resource
    private Table5Dao table5Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    @Override
    public Table5 queryById(String stuId) {
        return this.table5Dao.queryById(stuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Table5> queryAllByLimit(int offset, int limit) {
        return this.table5Dao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param table5 实例对象
     * @return 实例对象
     */
    @Override
    public Table5 insert(Table5 table5) {
        this.table5Dao.insert(table5);
        return table5;
    }

    /**
     * 修改数据
     *
     * @param table5 实例对象
     * @return 实例对象
     */
    @Override
    public Table5 update(Table5 table5) {
        this.table5Dao.update(table5);
        return this.queryById(table5.getStuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String stuId) {
        return this.table5Dao.deleteById(stuId) > 0;
    }
}