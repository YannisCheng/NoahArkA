package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.TownTable;
import com.cwj.datasource.mysql.dao.TownTableDao;
import com.cwj.datasource.mysql.service.TownTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TownTable)表服务实现类
 *
 * @author makejava
 * @since 2021-08-02 20:55:28
 */
@Service("townTableService")
public class TownTableServiceImpl implements TownTableService {
    @Resource
    private TownTableDao townTableDao;

    /**
     * 通过ID查询单条数据
     *
     * @param townCode 主键
     * @return 实例对象
     */
    @Override
    public TownTable queryById(String townCode) {
        return this.townTableDao.queryById(townCode);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TownTable> queryAllByLimit(int offset, int limit) {
        return this.townTableDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param townTable 实例对象
     * @return 实例对象
     */
    @Override
    public TownTable insert(TownTable townTable) {
        this.townTableDao.insert(townTable);
        return townTable;
    }

    /**
     * 修改数据
     *
     * @param townTable 实例对象
     * @return 实例对象
     */
    @Override
    public TownTable update(TownTable townTable) {
        this.townTableDao.update(townTable);
        return this.queryById(townTable.getTownCode());
    }

    /**
     * 通过主键删除数据
     *
     * @param townCode 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String townCode) {
        return this.townTableDao.deleteById(townCode) > 0;
    }
}