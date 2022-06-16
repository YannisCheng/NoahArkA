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
@Table(name = "sys_job")
@ApiModel(value = "SysJob", description = "定时任务调度表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "sys_job", comment = "定时任务调度表")
/**
 * 定时任务调度表(SysJob)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public class SysJob implements Serializable {

    private static final long serialVersionUID = -81950586769475673L;


    @Column(name = "id")
    @ApiModelProperty("任务ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "job_name")
    @ApiModelProperty("任务名称")
    public String jobName;

    @Column(name = "job_group")
    @ApiModelProperty("任务组名")
    public String jobGroup;

    @Column(name = "invoke_target")
    @ApiModelProperty("调用目标字符串")
    public String invokeTarget;

    @Column(name = "cron_expression")
    @ApiModelProperty("cron执行表达式")
    public String cronExpression;

    @Column(name = "misfire_policy")
    @ApiModelProperty("计划执行错误策略（1立即执行 2执行一次 3放弃执行）")
    public String misfirePolicy;

    @Column(name = "concurrent")
    @ApiModelProperty("是否并发执行（0允许 1禁止）")
    public String concurrent;

    @Column(name = "status")
    @ApiModelProperty("状态（0正常 1暂停）")
    public String status;

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
    @ApiModelProperty("备注信息")
    public String remark;

}