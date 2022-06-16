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
@Table(name = "sys_notice")
@ApiModel(value = "SysNotice", description = "通知公告表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "sys_notice", comment = "通知公告表")
/**
 * 通知公告表(SysNotice)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public class SysNotice implements Serializable {

    private static final long serialVersionUID = 983568792479748535L;


    @Column(name = "id")
    @ApiModelProperty("公告ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "notice_title")
    @ApiModelProperty("公告标题")
    public String noticeTitle;

    @Column(name = "notice_type")
    @ApiModelProperty("公告类型（1通知 2公告）")
    public String noticeType;

    @Column(name = "notice_content")
    @ApiModelProperty("公告内容")
    public String noticeContent;

    @Column(name = "status")
    @ApiModelProperty("公告状态（0正常 1关闭）")
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
    @ApiModelProperty("备注")
    public String remark;

}