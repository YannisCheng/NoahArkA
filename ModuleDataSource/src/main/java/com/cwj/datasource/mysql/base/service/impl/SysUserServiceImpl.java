package com.cwj.datasource.mysql.base.service.impl;

import com.cwj.datasource.mysql.base.entity.SysUser;
import com.cwj.datasource.mysql.base.repository.SysUserRepository;
import com.cwj.datasource.mysql.base.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<SysUser> findById(Long id) {
        return sysUserRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<SysUser> findAll(Sort sort) {
        return sysUserRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<SysUser> findByPage(Pageable pageable) {
        return sysUserRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysUser saveOne(SysUser sysUser) {
        return sysUserRepository.save(sysUser);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysUser updateByObj(SysUser sysUser) {
        Optional<SysUser> optional = sysUserRepository.findById(sysUser.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(sysUser, optional.get());
            return sysUserRepository.save(optional.get());
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
        sysUserRepository.deleteById(id);
    }
}