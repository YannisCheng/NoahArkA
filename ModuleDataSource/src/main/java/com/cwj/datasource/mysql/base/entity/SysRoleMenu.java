package com.cwj.datasource.mysql.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "sys_role_menu")
@ApiModel(value = "SysRoleMenu", description = "角色和菜单关联表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "sys_role_menu", comment = "角色和菜单关联表")
/**
 * 角色和菜单关联表(SysRoleMenu)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 665901339269782539L;


    @Column(name = "id")
    @ApiModelProperty("主键id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "role_id")
    @ApiModelProperty("角色ID")
    public Long roleId;

    @Column(name = "menu_id")
    @ApiModelProperty("菜单ID")
    public Long menuId;

}