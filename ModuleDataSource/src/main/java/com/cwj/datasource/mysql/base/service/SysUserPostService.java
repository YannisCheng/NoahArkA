package com.cwj.datasource.mysql.base.service;

import com.cwj.datasource.mysql.base.entity.SysUserPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * 用户与岗位关联表(SysUserPost)表服务接口
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysUserPostService {


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Optional<SysUserPost> findById(Integer id);


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    List<SysUserPost> findAll(Sort sort);


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    Page<SysUserPost> findByPage(Pageable pageable);


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    SysUserPost saveOne(SysUserPost sysUserPost);


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    SysUserPost updateByObj(SysUserPost sysUserPost);


    // --------------------------------------  删  --------------------------------------

    /**
     * 通过主键id删除单条数据
     *
     * @param id 主键
     */
    void deleteById(Integer id);
}