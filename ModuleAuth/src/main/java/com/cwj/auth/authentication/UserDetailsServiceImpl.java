package com.cwj.auth.authentication;

import com.cwj.datasource.mysql.base.entity.SysUser;
import com.cwj.datasource.mysql.base.service.SysRoleMenuService;
import com.cwj.datasource.mysql.base.service.SysUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UserDetailsServiceImpl  实现UserDetailsService接口，从系统DB获取当前用户信息，并返回UserDetails类。
 *
 * @author ChengWenjia
 * @since 2022/2/11 08:56
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Resource
    SysRoleMenuService sysRoleMenuService;

    @Resource
    private SysUserInfoService sysUserInfoService;

    @Override
    public UserDetails loadUserByUsername(String userContent) throws UsernameNotFoundException {

        //logger.info("UserDetailService ---> : " + userEmail);
        UserDetails userDetails;

        if (userContent.contains("@")) {
            // 登录内容为邮箱
            SysUser metaUser = sysUserInfoService.selectUserByUserEmail(userContent);
            userDetails = createLoginUser(metaUser);
        } else {
            // 登录内容为手机号
            SysUser metaUser = sysUserInfoService.findByPhonenumber(userContent);
            userDetails = createLoginUser(metaUser);
        }

        return userDetails;
    }

    public UserDetails createLoginUser(SysUser sysUser) {
        //LoginUserDetails loginUserDetails = new LoginUserDetails(metaUser.getUserId(), metaUser);
        LoginUserDetails loginUserDetails = new LoginUserDetails(sysUser.getId(), sysUser, sysRoleMenuService.getMenu(sysUser));
        logger.warn("new LoginUserDetails is: {}", loginUserDetails.toString());
        //logger.info("登录用户 ：{} ", loginUserDetails.toString());
        return loginUserDetails;
    }
}
