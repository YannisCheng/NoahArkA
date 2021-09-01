package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.CityTable;
import com.cwj.datasource.mysql.dao.CityTableDao;
import com.cwj.datasource.mysql.service.CityTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CityTable)表服务实现类
 *
 * @author makejava
 * @since 2021-08-02 11:04:48
 */
@Service("cityTableService")
public class CityTableServiceImpl implements CityTableService {
    @Resource
    private CityTableDao cityTableDao;

    /**
     * 通过ID查询单条数据
     *
     * @param cityCode 主键
     * @return 实例对象
     */
    @Override
    public CityTable queryById(String cityCode) {
        return this.cityTableDao.queryById(cityCode);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<CityTable> queryAllByLimit(int offset, int limit) {
        return this.cityTableDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param cityTable 实例对象
     * @return 实例对象
     */
    @Override
    public CityTable insert(CityTable cityTable) {
        this.cityTableDao.insert(cityTable);
        return cityTable;
    }

    /**
     * 修改数据
     *
     * @param cityTable 实例对象
     * @return 实例对象
     */
    @Override
    public CityTable update(CityTable cityTable) {
        this.cityTableDao.update(cityTable);
        return this.queryById(cityTable.getCityCode());
    }

    /**
     * 通过主键删除数据
     *
     * @param cityCode 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String cityCode) {
        return this.cityTableDao.deleteById(cityCode) > 0;
    }
}