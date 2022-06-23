package com.cwj.auth.authentication;

import com.alibaba.fastjson.annotation.JSONField;
import com.cwj.datasource.mysql.base.entity.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * LoginUserDetails Authentication中 UserDetails接口 的具体实现
 *
 * @author ChengWenjia
 * @since 2022-02-09 11:31
 */
@Data
public class LoginUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */
    private SysUser sysUser;

    public LoginUserDetails() {
    }

    public LoginUserDetails(Long userId, SysUser sysUser) {
        this.userId = userId;
        this.sysUser = sysUser;
    }

    public LoginUserDetails(Long userId, SysUser sysUser, Set<String> permissions) {
        this.userId = userId;
        this.sysUser = sysUser;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JSONField(serialize = false)
    @Override
    public String getPassword() {
        // 加密处理
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getEmail();
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     */
    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     */
    @JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
