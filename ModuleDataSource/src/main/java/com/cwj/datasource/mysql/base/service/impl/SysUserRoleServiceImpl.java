package com.cwj.datasource.mysql.base.service.impl;

import com.cwj.datasource.mysql.base.entity.SysUserRole;
import com.cwj.datasource.mysql.base.repository.SysUserRoleRepository;
import com.cwj.datasource.mysql.base.service.SysUserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 用户和角色关联表(SysUserRole)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleRepository sysUserRoleRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<SysUserRole> findById(Integer id) {
        return sysUserRoleRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<SysUserRole> findAll(Sort sort) {
        return sysUserRoleRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<SysUserRole> findByPage(Pageable pageable) {
        return sysUserRoleRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysUserRole saveOne(SysUserRole sysUserRole) {
        return sysUserRoleRepository.save(sysUserRole);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysUserRole updateByObj(SysUserRole sysUserRole) {
        Optional<SysUserRole> optional = sysUserRoleRepository.findById(sysUserRole.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(sysUserRole, optional.get());
            return sysUserRoleRepository.save(optional.get());
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
        sysUserRoleRepository.deleteById(id);
    }
}