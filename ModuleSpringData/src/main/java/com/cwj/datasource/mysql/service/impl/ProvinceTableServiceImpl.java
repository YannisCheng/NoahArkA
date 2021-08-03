package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.ProvinceTable;
import com.cwj.datasource.mysql.dao.ProvinceTableDao;
import com.cwj.datasource.mysql.service.ProvinceTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ProvinceTable)表服务实现类
 *
 * @author makejava
 * @since 2021-08-02 20:53:07
 */
@Service("provinceTableService")
public class ProvinceTableServiceImpl implements ProvinceTableService {
    @Resource
    private ProvinceTableDao provinceTableDao;

    /**
     * 通过ID查询单条数据
     *
     * @param provinceCode 主键
     * @return 实例对象
     */
    @Override
    public ProvinceTable queryById(String provinceCode) {
        return this.provinceTableDao.queryById(provinceCode);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ProvinceTable> queryAllByLimit(int offset, int limit) {
        return this.provinceTableDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param provinceTable 实例对象
     * @return 实例对象
     */
    @Override
    public ProvinceTable insert(ProvinceTable provinceTable) {
        this.provinceTableDao.insert(provinceTable);
        return provinceTable;
    }

    /**
     * 修改数据
     *
     * @param provinceTable 实例对象
     * @return 实例对象
     */
    @Override
    public ProvinceTable update(ProvinceTable provinceTable) {
        this.provinceTableDao.update(provinceTable);
        return this.queryById(provinceTable.getProvinceCode());
    }

    /**
     * 通过主键删除数据
     *
     * @param provinceCode 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String provinceCode) {
        return this.provinceTableDao.deleteById(provinceCode) > 0;
    }
}