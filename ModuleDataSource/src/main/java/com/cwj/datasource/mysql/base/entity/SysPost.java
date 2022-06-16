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
@Table(name = "sys_post")
@ApiModel(value = "SysPost", description = "岗位信息表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "sys_post", comment = "岗位信息表")
/**
 * 岗位信息表(SysPost)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public class SysPost implements Serializable {

    private static final long serialVersionUID = -71204072114231110L;


    @Column(name = "id")
    @ApiModelProperty("岗位ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "post_code")
    @ApiModelProperty("岗位编码")
    public String postCode;

    @Column(name = "post_name")
    @ApiModelProperty("岗位名称")
    public String postName;

    @Column(name = "post_sort")
    @ApiModelProperty("显示顺序")
    public Integer postSort;

    @Column(name = "status")
    @ApiModelProperty("状态（0正常 1停用）")
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