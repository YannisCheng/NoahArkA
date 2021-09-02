package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.Table3;
import com.cwj.datasource.mysql.dao.Table3Dao;
import com.cwj.datasource.mysql.service.Table3Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Table3)表服务实现类
 *
 * @author makejava
 * @since 2021-09-02 21:26:43
 */
@Service("table3Service")
public class Table3ServiceImpl implements Table3Service {
    @Resource
    private Table3Dao table3Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    @Override
    public Table3 queryById(String stuId) {
        return this.table3Dao.queryById(stuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Table3> queryAllByLimit(int offset, int limit) {
        return this.table3Dao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param table3 实例对象
     * @return 实例对象
     */
    @Override
    public Table3 insert(Table3 table3) {
        this.table3Dao.insert(table3);
        return table3;
    }

    /**
     * 修改数据
     *
     * @param table3 实例对象
     * @return 实例对象
     */
    @Override
    public Table3 update(Table3 table3) {
        this.table3Dao.update(table3);
        return this.queryById(table3.getStuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String stuId) {
        return this.table3Dao.deleteById(stuId) > 0;
    }
}