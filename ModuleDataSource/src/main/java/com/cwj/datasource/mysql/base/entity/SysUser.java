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
@Table(name = "sys_user")
@ApiModel(value = "SysUser", description = "用户信息表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "sys_user", comment = "用户信息表")
/**
 * 用户信息表(SysUser)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public class SysUser implements Serializable {

    private static final long serialVersionUID = -20282245500729767L;


    @Column(name = "id")
    @ApiModelProperty("用户ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "dept_id")
    @ApiModelProperty("部门ID")
    public Long deptId;

    @Column(name = "user_name")
    @ApiModelProperty("用户账号")
    public String userName;

    @Column(name = "nick_name")
    @ApiModelProperty("用户昵称")
    public String nickName;

    @Column(name = "user_type")
    @ApiModelProperty("用户类型（00系统用户）")
    public String userType;

    @Column(name = "email")
    @ApiModelProperty("用户邮箱")
    public String email;

    @Column(name = "phonenumber")
    @ApiModelProperty("手机号码")
    public String phonenumber;

    @Column(name = "sex")
    @ApiModelProperty("用户性别（0男 1女 2未知）")
    public String sex;

    @Column(name = "avatar")
    @ApiModelProperty("头像地址")
    public String avatar;

    @Column(name = "password")
    @ApiModelProperty("密码")
    public String password;

    @Column(name = "status")
    @ApiModelProperty("帐号状态（0正常 1停用）")
    public String status;

    @Column(name = "del_flag")
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    public String delFlag;

    @Column(name = "login_ip")
    @ApiModelProperty("最后登录IP")
    public String loginIp;

    @Column(name = "login_date")
    @ApiModelProperty("最后登录时间")
    public Date loginDate;

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