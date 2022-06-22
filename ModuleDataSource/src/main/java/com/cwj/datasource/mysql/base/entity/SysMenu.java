package com.cwj.datasource.mysql.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "sys_menu")
@ApiModel(value = "SysMenu", description = "菜单权限表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "sys_menu", comment = "菜单权限表")
/**
 * 菜单权限表(SysMenu)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public class SysMenu implements Serializable {

    private static final long serialVersionUID = -46600137396262041L;


    @Column(name = "id")
    @ApiModelProperty("菜单ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "menu_name")
    @ApiModelProperty("菜单名称")
    public String menuName;

    @Column(name = "parent_id")
    @ApiModelProperty("父菜单ID")
    public Long parentId;

    @Column(name = "order_num")
    @ApiModelProperty("显示顺序")
    public Integer orderNum;

    @Column(name = "path")
    @ApiModelProperty("路由地址")
    public String path;

    @Column(name = "component")
    @ApiModelProperty("组件路径")
    public String component;

    @Column(name = "query")
    @ApiModelProperty("路由参数")
    public String query;

    @Column(name = "is_frame")
    @ApiModelProperty("是否为外链（0是 1否）")
    public Integer isFrame;

    @Column(name = "is_cache")
    @ApiModelProperty("是否缓存（0缓存 1不缓存）")
    public Integer isCache;

    @Column(name = "menu_type")
    @ApiModelProperty("菜单类型（M目录 C菜单 F按钮）")
    public String menuType;

    @Column(name = "visible")
    @ApiModelProperty("菜单状态（0显示 1隐藏）")
    public String visible;

    @Column(name = "status")
    @ApiModelProperty("菜单状态（0正常 1停用）")
    public String status;

    @Column(name = "perms")
    @ApiModelProperty("权限标识")
    public String perms;

    @Column(name = "icon")
    @ApiModelProperty("菜单图标")
    public String icon;

    @Column(name = "create_by")
    @ApiModelProperty("创建者")
    public String createBy;

    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    public Date createTime;

    @Column(name = "update_by")
    @ApiModelProperty("更新者")
    public String updateBy;

    @Column(name = "update_time")
    @ApiModelProperty("更新时间")
    public Date updateTime;

    @Column(name = "remark")
    @ApiModelProperty("备注")
    public String remark;

    @Transient
    @ApiModelProperty("子菜单")
    private List<SysMenu> children = new ArrayList<SysMenu>();

}