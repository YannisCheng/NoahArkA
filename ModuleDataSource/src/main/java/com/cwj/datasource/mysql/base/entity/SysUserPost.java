package com.cwj.datasource.mysql.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "sys_user_post")
@ApiModel(value = "SysUserPost", description = "用户与岗位关联表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "sys_user_post", comment = "用户与岗位关联表")
/**
 * 用户与岗位关联表(SysUserPost)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public class SysUserPost implements Serializable {

    private static final long serialVersionUID = -24223270925979361L;


    @Column(name = "id")
    @ApiModelProperty("主键id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "user_id")
    @ApiModelProperty("用户ID")
    public Long userId;

    @Column(name = "post_id")
    @ApiModelProperty("岗位ID")
    public Long postId;

}