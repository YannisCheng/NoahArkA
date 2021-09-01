package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.CountyTable;
import com.cwj.datasource.mysql.dao.CountyTableDao;
import com.cwj.datasource.mysql.service.CountyTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CountyTable)表服务实现类
 *
 * @author makejava
 * @since 2021-08-02 20:38:19
 */
@Service("countyTableService")
public class CountyTableServiceImpl implements CountyTableService {
    @Resource
    private CountyTableDao countyTableDao;

    /**
     * 通过ID查询单条数据
     *
     * @param countyCode 主键
     * @return 实例对象
     */
    @Override
    public CountyTable queryById(String countyCode) {
        return this.countyTableDao.queryById(countyCode);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<CountyTable> queryAllByLimit(int offset, int limit) {
        return this.countyTableDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param countyTable 实例对象
     * @return 实例对象
     */
    @Override
    public CountyTable insert(CountyTable countyTable) {
        this.countyTableDao.insert(countyTable);
        return countyTable;
    }

    /**
     * 修改数据
     *
     * @param countyTable 实例对象
     * @return 实例对象
     */
    @Override
    public CountyTable update(CountyTable countyTable) {
        this.countyTableDao.update(countyTable);
        return this.queryById(countyTable.getCountyCode());
    }

    /**
     * 通过主键删除数据
     *
     * @param countyCode 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String countyCode) {
        return this.countyTableDao.deleteById(countyCode) > 0;
    }
}