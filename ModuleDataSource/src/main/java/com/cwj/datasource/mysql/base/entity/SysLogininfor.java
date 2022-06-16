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
@Table(name = "sys_logininfor")
@ApiModel(value = "SysLogininfor", description = "系统访问记录")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "sys_logininfor", comment = "系统访问记录")
/**
 * 系统访问记录(SysLogininfor)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public class SysLogininfor implements Serializable {

    private static final long serialVersionUID = -65004920437587935L;


    @Column(name = "id")
    @ApiModelProperty("访问ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "user_name")
    @ApiModelProperty("用户账号")
    public String userName;

    @Column(name = "ipaddr")
    @ApiModelProperty("登录IP地址")
    public String ipaddr;

    @Column(name = "login_location")
    @ApiModelProperty("登录地点")
    public String loginLocation;

    @Column(name = "browser")
    @ApiModelProperty("浏览器类型")
    public String browser;

    @Column(name = "os")
    @ApiModelProperty("操作系统")
    public String os;

    @Column(name = "status")
    @ApiModelProperty("登录状态（0成功 1失败）")
    public String status;

    @Column(name = "msg")
    @ApiModelProperty("提示消息")
    public String msg;

    @Column(name = "login_time")
    @ApiModelProperty("访问时间")
    public Date loginTime;

}