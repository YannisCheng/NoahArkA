package com.cwj.datasource.mysql.base.service;

import com.cwj.datasource.mysql.base.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * 用户信息表(SysUser)表服务接口
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysUserInfoService {


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Optional<SysUser> findById(Long id);


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    List<SysUser> findAll(Sort sort);


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    Page<SysUser> findByPage(Pageable pageable);


    /**
     * 通过userEmail查询用户
     *
     * @param userEmail userEmail
     * @return 用户对象信息
     */
    SysUser selectUserByUserEmail(String userEmail);


    /**
     * 通过phonenumber查询用户
     *
     * @param phonenumber 手机号
     * @return 用户对象信息
     */
    SysUser findByPhonenumber(String phonenumber);

    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    SysUser saveOne(SysUser sysUser);


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    SysUser updateByObj(SysUser sysUser);


    // --------------------------------------  删  --------------------------------------

    /**
     * 通过主键id删除单条数据
     *
     * @param id 主键
     */
    void deleteById(Long id);
}