package com.cwj.datasource.mysql.area.service.impl;


import com.cwj.datasource.mysql.area.entity.CityTable;
import com.cwj.datasource.mysql.area.repository.CityTableRepository;
import com.cwj.datasource.mysql.area.service.CityTableService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 城市表(CityTable)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:45:01
 */
@Service("cityTableService")
public class CityTableServiceImpl implements CityTableService {

    @Autowired
    private CityTableRepository cityTableRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<CityTable> findById(Integer id) {
        return cityTableRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<CityTable> findAll(Sort sort) {
        return cityTableRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<CityTable> findByPage(Pageable pageable) {
        return cityTableRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public CityTable saveOne(CityTable cityTable) {
        return cityTableRepository.save(cityTable);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public CityTable updateByObj(CityTable cityTable) {
        Optional<CityTable> optional = cityTableRepository.findById(cityTable.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(cityTable, optional.get());
            return cityTableRepository.save(optional.get());
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
        cityTableRepository.deleteById(id);
    }
}