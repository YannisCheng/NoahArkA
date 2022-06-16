package com.cwj.datasource.mysql.area.service.impl;


import com.cwj.datasource.mysql.area.entity.VillageTable;
import com.cwj.datasource.mysql.area.repository.VillageTableRepository;
import com.cwj.datasource.mysql.area.service.VillageTableService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 街道、社区、村表(VillageTable)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:45:03
 */
@Service("villageTableService")
public class VillageTableServiceImpl implements VillageTableService {

    @Autowired
    private VillageTableRepository villageTableRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<VillageTable> findById(Integer id) {
        return villageTableRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<VillageTable> findAll(Sort sort) {
        return villageTableRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<VillageTable> findByPage(Pageable pageable) {
        return villageTableRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public VillageTable saveOne(VillageTable villageTable) {
        return villageTableRepository.save(villageTable);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public VillageTable updateByObj(VillageTable villageTable) {
        Optional<VillageTable> optional = villageTableRepository.findById(villageTable.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(villageTable, optional.get());
            return villageTableRepository.save(optional.get());
        }
        return null;
    }


    // --------------------------------------  删  --------------------------------------

    /**
     * 通过主键id删除单条数据
     *
     * @param id 主键
     */
    @Override
    public void deleteById(Integer id) {
        villageTableRepository.deleteById(id);
    }
}