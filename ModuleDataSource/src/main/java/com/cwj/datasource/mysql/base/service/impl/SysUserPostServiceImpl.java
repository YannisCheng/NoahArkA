package com.cwj.datasource.mysql.base.service.impl;

import com.cwj.datasource.mysql.base.entity.SysUserPost;
import com.cwj.datasource.mysql.base.repository.SysUserPostRepository;
import com.cwj.datasource.mysql.base.service.SysUserPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 用户与岗位关联表(SysUserPost)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
@Service("sysUserPostService")
public class SysUserPostServiceImpl implements SysUserPostService {

    @Autowired
    private SysUserPostRepository sysUserPostRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<SysUserPost> findById(Integer id) {
        return sysUserPostRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<SysUserPost> findAll(Sort sort) {
        return sysUserPostRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<SysUserPost> findByPage(Pageable pageable) {
        return sysUserPostRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysUserPost saveOne(SysUserPost sysUserPost) {
        return sysUserPostRepository.save(sysUserPost);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysUserPost updateByObj(SysUserPost sysUserPost) {
        Optional<SysUserPost> optional = sysUserPostRepository.findById(sysUserPost.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(sysUserPost, optional.get());
            return sysUserPostRepository.save(optional.get());
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
        sysUserPostRepository.deleteById(id);
    }
}