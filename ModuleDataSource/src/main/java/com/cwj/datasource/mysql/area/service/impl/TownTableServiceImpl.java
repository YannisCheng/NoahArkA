package com.cwj.datasource.mysql.area.service.impl;


import com.cwj.datasource.mysql.area.entity.TownTable;
import com.cwj.datasource.mysql.area.repository.TownTableRepository;
import com.cwj.datasource.mysql.area.service.TownTableService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 乡镇表(TownTable)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:45:03
 */
@Service("townTableService")
public class TownTableServiceImpl implements TownTableService {

    @Autowired
    private TownTableRepository townTableRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<TownTable> findById(Integer id) {
        return townTableRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<TownTable> findAll(Sort sort) {
        return townTableRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<TownTable> findByPage(Pageable pageable) {
        return townTableRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public TownTable saveOne(TownTable townTable) {
        return townTableRepository.save(townTable);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public TownTable updateByObj(TownTable townTable) {
        Optional<TownTable> optional = townTableRepository.findById(townTable.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(townTable, optional.get());
            return townTableRepository.save(optional.get());
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
        townTableRepository.deleteById(id);
    }
}