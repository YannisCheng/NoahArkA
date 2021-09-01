package com.cwj.datasource.mysql.service;

import com.cwj.datasource.mysql.entity.ProvinceTable;
import java.util.List;

/**
 * (ProvinceTable)表服务接口
 *
 * @author makejava
 * @since 2021-08-02 20:53:07
 */
public interface ProvinceTableService {

    /**
     * 通过ID查询单条数据
     *
     * @param provinceCode 主键
     * @return 实例对象
     */
    ProvinceTable queryById(String provinceCode);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ProvinceTable> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param provinceTable 实例对象
     * @return 实例对象
     */
    ProvinceTable insert(ProvinceTable provinceTable);

    /**
     * 修改数据
     *
     * @param provinceTable 实例对象
     * @return 实例对象
     */
    ProvinceTable update(ProvinceTable provinceTable);

    /**
     * 通过主键删除数据
     *
     * @param provinceCode 主键
     * @return 是否成功
     */
    boolean deleteById(String provinceCode);

}