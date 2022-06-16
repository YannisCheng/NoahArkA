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
@Table(name = "sys_dept")
@ApiModel(value = "SysDept", description = "部门表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "sys_dept", comment = "部门表")
/**
 * 部门表(SysDept)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:11
 */
public class SysDept implements Serializable {

    private static final long serialVersionUID = -18358729259335837L;


    @Column(name = "id")
    @ApiModelProperty("部门id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "parent_id")
    @ApiModelProperty("父部门id")
    public Long parentId;

    @Column(name = "ancestors")
    @ApiModelProperty("祖级列表")
    public String ancestors;

    @Column(name = "dept_name")
    @ApiModelProperty("部门名称")
    public String deptName;

    @Column(name = "order_num")
    @ApiModelProperty("显示顺序")
    public Integer orderNum;

    @Column(name = "leader")
    @ApiModelProperty("负责人")
    public String leader;

    @Column(name = "phone")
    @ApiModelProperty("联系电话")
    public String phone;

    @Column(name = "email")
    @ApiModelProperty("邮箱")
    public String email;

    @Column(name = "status")
    @ApiModelProperty("部门状态（0正常 1停用）")
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

}