package com.cwj.datasource.mysql.base.service.impl;

import com.cwj.datasource.mysql.base.entity.SysRoleDept;
import com.cwj.datasource.mysql.base.repository.SysRoleDeptRepository;
import com.cwj.datasource.mysql.base.service.SysRoleDeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 角色和部门关联表(SysRoleDept)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl implements SysRoleDeptService {

    @Autowired
    private SysRoleDeptRepository sysRoleDeptRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<SysRoleDept> findById(Integer id) {
        return sysRoleDeptRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<SysRoleDept> findAll(Sort sort) {
        return sysRoleDeptRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<SysRoleDept> findByPage(Pageable pageable) {
        return sysRoleDeptRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysRoleDept saveOne(SysRoleDept sysRoleDept) {
        return sysRoleDeptRepository.save(sysRoleDept);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysRoleDept updateByObj(SysRoleDept sysRoleDept) {
        Optional<SysRoleDept> optional = sysRoleDeptRepository.findById(sysRoleDept.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(sysRoleDept, optional.get());
            return sysRoleDeptRepository.save(optional.get());
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
        sysRoleDeptRepository.deleteById(id);
    }
}