package com.cwj.datasource.mysql.base.service.impl;

import com.cwj.datasource.mysql.base.entity.SysPost;
import com.cwj.datasource.mysql.base.repository.SysPostRepository;
import com.cwj.datasource.mysql.base.service.SysPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 岗位信息表(SysPost)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
@Service("sysPostService")
public class SysPostServiceImpl implements SysPostService {

    @Autowired
    private SysPostRepository sysPostRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<SysPost> findById(Long id) {
        return sysPostRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<SysPost> findAll(Sort sort) {
        return sysPostRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<SysPost> findByPage(Pageable pageable) {
        return sysPostRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysPost saveOne(SysPost sysPost) {
        return sysPostRepository.save(sysPost);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysPost updateByObj(SysPost sysPost) {
        Optional<SysPost> optional = sysPostRepository.findById(sysPost.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(sysPost, optional.get());
            return sysPostRepository.save(optional.get());
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
        sysPostRepository.deleteById(id);
    }
}