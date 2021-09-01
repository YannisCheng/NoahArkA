package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.VillageTable;
import com.cwj.datasource.mysql.dao.VillageTableDao;
import com.cwj.datasource.mysql.service.VillageTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (VillageTable)表服务实现类
 *
 * @author makejava
 * @since 2021-08-02 21:08:15
 */
@Service("villageTableService")
public class VillageTableServiceImpl implements VillageTableService {
    @Resource
    private VillageTableDao villageTableDao;

    /**
     * 通过ID查询单条数据
     *
     * @param villageCode 主键
     * @return 实例对象
     */
    @Override
    public VillageTable queryById(String villageCode) {
        return this.villageTableDao.queryById(villageCode);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<VillageTable> queryAllByLimit(int offset, int limit) {
        return this.villageTableDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param villageTable 实例对象
     * @return 实例对象
     */
    @Override
    public VillageTable insert(VillageTable villageTable) {
        this.villageTableDao.insert(villageTable);
        return villageTable;
    }

    /**
     * 修改数据
     *
     * @param villageTable 实例对象
     * @return 实例对象
     */
    @Override
    public VillageTable update(VillageTable villageTable) {
        this.villageTableDao.update(villageTable);
        return this.queryById(villageTable.getVillageCode());
    }

    /**
     * 通过主键删除数据
     *
     * @param villageCode 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String villageCode) {
        return this.villageTableDao.deleteById(villageCode) > 0;
    }
}