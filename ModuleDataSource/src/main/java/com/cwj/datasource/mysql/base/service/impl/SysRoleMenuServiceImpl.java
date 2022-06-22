package com.cwj.datasource.mysql.base.service.impl;

import com.cwj.datasource.mysql.base.entity.SysRoleMenu;
import com.cwj.datasource.mysql.base.entity.SysUser;
import com.cwj.datasource.mysql.base.repository.SysRoleMenuRepository;
import com.cwj.datasource.mysql.base.service.SysMenuService;
import com.cwj.datasource.mysql.base.service.SysRoleMenuService;
import com.cwj.datasource.mysql.base.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuRepository sysRoleMenuRepository;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<SysRoleMenu> findById(Integer id) {
        return sysRoleMenuRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<SysRoleMenu> findAll(Sort sort) {
        return sysRoleMenuRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<SysRoleMenu> findByPage(Pageable pageable) {
        return sysRoleMenuRepository.findAll(pageable);
    }

    @Override
    public Set<String> getMenu(SysUser user) {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            perms.add("*:*:*");
        } else {
            perms.addAll(sysMenuService.findSysMenusByUserId(user.getId()));
        }
        return perms;
    }

    @Override
    public Set<String> getRole(SysUser user) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            roles.add("admin");
        } else {
            roles.addAll(sysRoleService.findSysRolesByUserId(user.getId()));
        }
        return roles;
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysRoleMenu saveOne(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuRepository.save(sysRoleMenu);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysRoleMenu updateByObj(SysRoleMenu sysRoleMenu) {
        Optional<SysRoleMenu> optional = sysRoleMenuRepository.findById(sysRoleMenu.id);
        boolean present = optional.isPresent();
        if (present) {
            SysRoleMenu menu = optional.get();
            BeanUtils.copyProperties(sysRoleMenu, menu);
            return sysRoleMenuRepository.save(menu);
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
        sysRoleMenuRepository.deleteById(id);
    }
}