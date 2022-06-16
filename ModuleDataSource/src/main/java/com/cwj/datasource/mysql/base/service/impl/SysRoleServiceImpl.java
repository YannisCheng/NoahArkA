package com.cwj.datasource.mysql.base.service.impl;

import com.cwj.datasource.mysql.base.entity.SysRole;
import com.cwj.datasource.mysql.base.repository.SysRoleRepository;
import com.cwj.datasource.mysql.base.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 角色信息表(SysRole)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleRepository sysRoleRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<SysRole> findById(Long id) {
        return sysRoleRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<SysRole> findAll(Sort sort) {
        return sysRoleRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<SysRole> findByPage(Pageable pageable) {
        return sysRoleRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysRole saveOne(SysRole sysRole) {
        return sysRoleRepository.save(sysRole);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysRole updateByObj(SysRole sysRole) {
        Optional<SysRole> optional = sysRoleRepository.findById(sysRole.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(sysRole, optional.get());
            return sysRoleRepository.save(optional.get());
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
        sysRoleRepository.deleteById(id);
    }
}