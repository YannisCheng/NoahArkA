package com.cwj.datasource.mysql.area.service.impl;

import com.cwj.datasource.mysql.area.entity.CountyTable;
import com.cwj.datasource.mysql.area.repository.CountyTableRepository;
import com.cwj.datasource.mysql.area.service.CountyTableService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 区县表(CountyTable)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:45:03
 */
@Service("countyTableService")
public class CountyTableServiceImpl implements CountyTableService {

    @Autowired
    private CountyTableRepository countyTableRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<CountyTable> findById(Integer id) {
        return countyTableRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<CountyTable> findAll(Sort sort) {
        return countyTableRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<CountyTable> findByPage(Pageable pageable) {
        return countyTableRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public CountyTable saveOne(CountyTable countyTable) {
        return countyTableRepository.save(countyTable);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public CountyTable updateByObj(CountyTable countyTable) {
        Optional<CountyTable> optional = countyTableRepository.findById(countyTable.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(countyTable, optional.get());
            return countyTableRepository.save(optional.get());
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
        countyTableRepository.deleteById(id);
    }
}