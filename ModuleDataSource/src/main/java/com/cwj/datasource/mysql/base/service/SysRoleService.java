package com.cwj.datasource.mysql.base.service;

import com.cwj.datasource.mysql.base.entity.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 角色信息表(SysRole)表服务接口
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysRoleService {


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Optional<SysRole> findById(Long id);


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    List<SysRole> findAll(Sort sort);


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    Page<SysRole> findByPage(Pageable pageable);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> findSysRolesByUserId(Long userId);


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    SysRole saveOne(SysRole sysRole);


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    SysRole updateByObj(SysRole sysRole);


    // --------------------------------------  删  --------------------------------------

    /**
     * 通过主键id删除单条数据
     *
     * @param id 主键
     */
    void deleteById(Long id);
}