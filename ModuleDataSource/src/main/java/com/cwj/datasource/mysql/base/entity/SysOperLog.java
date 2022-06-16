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
@Table(name = "sys_oper_log")
@ApiModel(value = "SysOperLog", description = "操作日志记录")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "sys_oper_log", comment = "操作日志记录")
/**
 * 操作日志记录(SysOperLog)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public class SysOperLog implements Serializable {

    private static final long serialVersionUID = 593701936803907091L;


    @Column(name = "id")
    @ApiModelProperty("日志主键")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "title")
    @ApiModelProperty("模块标题")
    public String title;

    @Column(name = "business_type")
    @ApiModelProperty("业务类型（0其它 1新增 2修改 3删除）")
    public Integer businessType;

    @Column(name = "method")
    @ApiModelProperty("方法名称")
    public String method;

    @Column(name = "request_method")
    @ApiModelProperty("请求方式")
    public String requestMethod;

    @Column(name = "operator_type")
    @ApiModelProperty("操作类别（0其它 1后台用户 2手机端用户）")
    public Integer operatorType;

    @Column(name = "oper_name")
    @ApiModelProperty("操作人员")
    public String operName;

    @Column(name = "dept_name")
    @ApiModelProperty("部门名称")
    public String deptName;

    @Column(name = "oper_url")
    @ApiModelProperty("请求URL")
    public String operUrl;

    @Column(name = "oper_ip")
    @ApiModelProperty("主机地址")
    public String operIp;

    @Column(name = "oper_location")
    @ApiModelProperty("操作地点")
    public String operLocation;

    @Column(name = "oper_param")
    @ApiModelProperty("请求参数")
    public String operParam;

    @Column(name = "json_result")
    @ApiModelProperty("返回参数")
    public String jsonResult;

    @Column(name = "status")
    @ApiModelProperty("操作状态（0正常 1异常）")
    public Integer status;

    @Column(name = "error_msg")
    @ApiModelProperty("错误消息")
    public String errorMsg;

    @Column(name = "oper_time")
    @ApiModelProperty("操作时间")
    public Date operTime;

}