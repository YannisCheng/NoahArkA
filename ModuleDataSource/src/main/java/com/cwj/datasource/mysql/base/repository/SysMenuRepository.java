package com.cwj.datasource.mysql.base.repository;

import com.cwj.datasource.mysql.base.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * 菜单权限表(SysMenu)表数据库访问层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public interface SysMenuRepository extends JpaRepository<SysMenu, Long> {

    /**
     * 根据用户id查询当前用户的所有menu权限字符串
     *
     * @param userId 用户id
     * @return 当前用户的所有权限
     */
    @Query(value = "select distinct menu.perms " +
            "from sys_menu menu " +
            "left join sys_role_menu sp on menu.id = sp.menu_id " +
            "left join sys_user_role sur on sp.role_id = sur.role_id " +
            "left join sys_role sr on sur.role_id = sr.id " +
            "where menu.status = '0' and sr.status = '0' and sur.user_id = ?1",
            nativeQuery = true)
    List<String> selectMenuPermsByUserId(Long userId);

    @Query(value = "select distinct * " +
            "from sys_menu menu " +
            "where " +
            " if(?1 != '', menu.name like concat('%',?1,'%') ,1=1) and" +
            " if(?2 != '', menu.status = ?2 ,1=1) " +
            "order by menu.pid, menu.sort",
            nativeQuery = true)
    List<SysMenu> findAllWithParam(String name, String status);

    /**
     * 根据用户id查询当前用户的所有menu权限对象
     *
     * @param userId 用户id
     * @return 当前用户的所有权限
     */
    @Query(value = "select distinct * " +
            "from sys_menu menu " +
            "left join sys_role_menu sp on menu.id = sp.menu_id " +
            "left join sys_user_role sur on sp.role_id = sur.role_id " +
            "left join sys_role sr on sur.role_id = sr.id " +
            "where menu.status = '0' and sr.status = '0' and sur.user_id = ?1 and" +
            " if(?2 != '', menu.name like concat('%',?2,'%') ,1=1) and" +
            " if(?3 != '', menu.status = ?3 ,1=1) " +
            "order by menu.pid, menu.sort",
            nativeQuery = true)
    List<SysMenu> selectMenuByUserId(Long userId, String name, String status);

    /**
     * 根据用户id查询当前用户的所有menu对象
     *
     * @param userId 用户id
     * @return 当前用户的所有权限
     */
    @Query(value = "select distinct * " +
            "from sys_menu menu " +
            "left join sys_role_menu sp on menu.id = sp.menu_id " +
            "left join sys_user_role sur on sp.role_id = sur.role_id " +
            "left join sys_role sr on sur.role_id = sr.id " +
            "where menu.status = '0' and sr.status = '0' and sur.user_id = ?1 and menu.type in ('M','C')" +
            " order by menu.pid, menu.sort",
            nativeQuery = true)
    List<SysMenu> selectMenuTreeByUserId(Long userId);


    @Query(value = "select distinct * from sys_menu where type in ('M','C') and status = 0 order by pid, sort",
            nativeQuery = true)
    List<SysMenu> selectMenuTreeAll();

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId            角色ID
     * @param menuCheckStrictly 菜单树选择项是否关联显示
     * @return 选中菜单列表
     */
    @Query(value = "select m.id " +
            "from sys_menu m left join sys_role_menu p on m.id = p.menu_id " +
            "where p.role_id = ?1 " +
            "and if(?2 = true, m.id not in (" +
            "select sm.pid from sys_menu sm inner join sys_role_menu sp " +
            "on sm.id = sp.menu_id and sp.role_id = ?1)" +
            ",1=1) " +
            "order by m.pid,m.sort",
            nativeQuery = true)
    List<Long> selectMenuListByRoleId(Long roleId, boolean menuCheckStrictly);

    boolean existsSysMenuByMenuNameAndParentId(String menuName, long parentId);


}