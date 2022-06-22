package com.cwj.datasource.mysql.base.service;

import com.cwj.datasource.mysql.base.entity.SysMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 菜单权限表(SysMenu)表服务接口
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysMenuService {


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Optional<SysMenu> findById(Long id);


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    List<SysMenu> findAll(Sort sort);


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    Page<SysMenu> findByPage(Pageable pageable);

    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    //List<SysMenu> selectMenuTreeByUserId(Long userId);
    //
    ///**
    // * 根据角色ID查询菜单树信息
    // *
    // * @param roleId 角色ID
    // * @return 选中菜单列表
    // */
    //List<Long> selectMenuListByRoleId(Long roleId);


    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> findSysMenusByUserId(Long userId);


    public List<SysMenu> selectMenuByUserId(Long userId, String name, String status);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean checkMenuNameUnique(SysMenu menu);


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    SysMenu saveOne(SysMenu sysMenu);


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    SysMenu updateByObj(SysMenu sysMenu);


    // --------------------------------------  删  --------------------------------------

    /**
     * 通过主键id删除单条数据
     *
     * @param id 主键
     */
    void deleteById(Long id);
}