package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.Table4;
import com.cwj.datasource.mysql.dao.Table4Dao;
import com.cwj.datasource.mysql.service.Table4Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Table4)表服务实现类
 *
 * @author makejava
 * @since 2021-09-02 21:27:30
 */
@Service("table4Service")
public class Table4ServiceImpl implements Table4Service {
    @Resource
    private Table4Dao table4Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    @Override
    public Table4 queryById(String stuId) {
        return this.table4Dao.queryById(stuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Table4> queryAllByLimit(int offset, int limit) {
        return this.table4Dao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param table4 实例对象
     * @return 实例对象
     */
    @Override
    public Table4 insert(Table4 table4) {
        this.table4Dao.insert(table4);
        return table4;
    }

    /**
     * 修改数据
     *
     * @param table4 实例对象
     * @return 实例对象
     */
    @Override
    public Table4 update(Table4 table4) {
        this.table4Dao.update(table4);
        return this.queryById(table4.getStuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String stuId) {
        return this.table4Dao.deleteById(stuId) > 0;
    }
}