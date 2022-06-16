package com.cwj.datasource.mysql.area.service.impl;


import com.cwj.datasource.mysql.area.entity.ProvinceTable;
import com.cwj.datasource.mysql.area.repository.ProvinceTableRepository;
import com.cwj.datasource.mysql.area.service.ProvinceTableService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 省份表(ProvinceTable)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:45:03
 */
@Service("provinceTableService")
public class ProvinceTableServiceImpl implements ProvinceTableService {

    @Autowired
    private ProvinceTableRepository provinceTableRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<ProvinceTable> findById(Integer id) {
        return provinceTableRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<ProvinceTable> findAll(Sort sort) {
        return provinceTableRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<ProvinceTable> findByPage(Pageable pageable) {
        return provinceTableRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public ProvinceTable saveOne(ProvinceTable provinceTable) {
        return provinceTableRepository.save(provinceTable);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public ProvinceTable updateByObj(ProvinceTable provinceTable) {
        Optional<ProvinceTable> optional = provinceTableRepository.findById(provinceTable.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(provinceTable, optional.get());
            return provinceTableRepository.save(optional.get());
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
        provinceTableRepository.deleteById(id);
    }
}