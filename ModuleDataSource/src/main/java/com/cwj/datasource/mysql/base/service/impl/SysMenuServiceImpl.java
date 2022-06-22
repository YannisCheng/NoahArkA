package com.cwj.datasource.mysql.base.service.impl;

import com.cwj.datasource.mysql.base.entity.SysMenu;
import com.cwj.datasource.mysql.base.entity.SysUser;
import com.cwj.datasource.mysql.base.repository.SysMenuRepository;
import com.cwj.datasource.mysql.base.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 菜单权限表(SysMenu)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuRepository sysMenuRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<SysMenu> findById(Long id) {
        return sysMenuRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<SysMenu> findAll(Sort sort) {
        return sysMenuRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<SysMenu> findByPage(Pageable pageable) {
        return sysMenuRepository.findAll(pageable);
    }

    //@Override
    //public List<SysMenu> selectMenuTreeByUserId(Long userId) {
    //    List<SysMenu> menus = null;
    //    if (SecurityUtil.isAdmin(userId)) {
    //        menus = sysMenuRepository.selectMenuTreeAll();
    //    } else {
    //        menus = sysMenuRepository.selectMenuTreeByUserId(userId);
    //    }
    //    return getChildPerms(menus, 0);
    //}
    //
    //@Override
    //public List<Long> selectMenuListByRoleId(Long roleId) {
    //    SysRole role = sysRoleRepository.findSysRoleById(roleId);
    //    System.out.println("role obj is: " + role.toString());
    //    return sysMenuRepository.selectMenuListByRoleId(roleId, role.isMenuCheckStrictly());
    //}

    @Override
    public Set<String> findSysMenusByUserId(Long userId) {
        List<String> perms = sysMenuRepository.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<SysMenu> selectMenuByUserId(Long userId, String name, String status) {
        List<SysMenu> menuList = null;
        // 管理员显示所有菜单信息
        if (SysUser.isAdmin(userId)) {
            menuList = sysMenuRepository.findAllWithParam(name, status);
        } else {
            menuList = sysMenuRepository.selectMenuByUserId(userId, name, status);
        }
        return menuList;
    }

    @Override
    public boolean checkMenuNameUnique(SysMenu menu) {
        return sysMenuRepository.existsSysMenuByMenuNameAndParentId(menu.getMenuName(), menu.getParentId());
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysMenu saveOne(SysMenu sysMenu) {
        return sysMenuRepository.save(sysMenu);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysMenu updateByObj(SysMenu sysMenu) {
        Optional<SysMenu> optional = sysMenuRepository.findById(sysMenu.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(sysMenu, optional.get());
            return sysMenuRepository.save(optional.get());
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
        sysMenuRepository.deleteById(id);
    }
}