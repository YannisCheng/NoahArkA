package com.cwj.datasource.mysql.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "sys_role")
@ApiModel(value = "SysRole", description = "角色信息表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "sys_role", comment = "角色信息表")
/**
 * 角色信息表(SysRole)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public class SysRole implements Serializable {

    private static final long serialVersionUID = -92479085877167074L;


    @Column(name = "id")
    @ApiModelProperty("角色ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "role_name")
    @ApiModelProperty("角色名称")
    public String roleName;

    @Column(name = "role_key")
    @ApiModelProperty("角色权限字符串")
    public String roleKey;

    @Column(name = "role_sort")
    @ApiModelProperty("显示顺序")
    public Integer roleSort;

    @Column(name = "data_scope")
    @ApiModelProperty("数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）")
    public String dataScope;

    @Column(name = "menu_check_strictly")
    @ApiModelProperty("菜单树选择项是否关联显示")
    public Integer menuCheckStrictly;

    @Column(name = "dept_check_strictly")
    @ApiModelProperty("部门树选择项是否关联显示")
    public Integer deptCheckStrictly;

    @Column(name = "status")
    @ApiModelProperty("角色状态（0正常 1停用）")
    public String status;

    @Column(name = "del_flag")
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    public String delFlag;

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

}