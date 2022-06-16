package com.cwj.datasource.mysql.base.service.impl;

import com.cwj.datasource.mysql.base.entity.SysDept;
import com.cwj.datasource.mysql.base.repository.SysDeptRepository;
import com.cwj.datasource.mysql.base.service.SysDeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 部门表(SysDept)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:15
 */
@Service("sysDeptService")
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptRepository sysDeptRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<SysDept> findById(Long id) {
        return sysDeptRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<SysDept> findAll(Sort sort) {
        return sysDeptRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<SysDept> findByPage(Pageable pageable) {
        return sysDeptRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysDept saveOne(SysDept sysDept) {
        return sysDeptRepository.save(sysDept);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysDept updateByObj(SysDept sysDept) {
        Optional<SysDept> optional = sysDeptRepository.findById(sysDept.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(sysDept, optional.get());
            return sysDeptRepository.save(optional.get());
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
    public void deleteById(Long id) {
        sysDeptRepository.deleteById(id);
    }
}