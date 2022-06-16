package com.cwj.datasource.mysql.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "sys_user_role")
@ApiModel(value = "SysUserRole", description = "用户和角色关联表")
@EntityListeners(AuditingEntityListener.class)
@org.hibernate.annotations.Table(appliesTo = "sys_user_role", comment = "用户和角色关联表")
/**
 * 用户和角色关联表(SysUserRole)实体类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 309175833493341897L;


    @Column(name = "id")
    @ApiModelProperty("主键id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "user_id")
    @ApiModelProperty("用户ID")
    public Long userId;

    @Column(name = "role_id")
    @ApiModelProperty("角色ID")
    public Long roleId;

}