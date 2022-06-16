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
@Table(name = "sys_job_log")
@ApiModel(value = "SysJobLog", description = "定时任务调度日志表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "sys_job_log", comment = "定时任务调度日志表")
/**
 * 定时任务调度日志表(SysJobLog)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public class SysJobLog implements Serializable {

    private static final long serialVersionUID = -13210886965588780L;


    @Column(name = "id")
    @ApiModelProperty("任务日志ID")
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

    @Column(name = "job_message")
    @ApiModelProperty("日志信息")
    public String jobMessage;

    @Column(name = "status")
    @ApiModelProperty("执行状态（0正常 1失败）")
    public String status;

    @Column(name = "exception_info")
    @ApiModelProperty("异常信息")
    public String exceptionInfo;

    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    public Date createTime;

}