package com.cwj.datasource.mysql.base.service;

import com.cwj.datasource.mysql.base.entity.SysRoleMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务接口
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysRoleMenuService {


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Optional<SysRoleMenu> findById(Integer id);


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    List<SysRoleMenu> findAll(Sort sort);


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    Page<SysRoleMenu> findByPage(Pageable pageable);


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    SysRoleMenu saveOne(SysRoleMenu sysRoleMenu);


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    SysRoleMenu updateByObj(SysRoleMenu sysRoleMenu);


    // --------------------------------------  删  --------------------------------------

    /**
     * 通过主键id删除单条数据
     *
     * @param id 主键
     */
    void deleteById(Integer id);
}