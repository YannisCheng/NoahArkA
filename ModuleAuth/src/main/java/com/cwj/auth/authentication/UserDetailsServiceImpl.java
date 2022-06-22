package com.cwj.auth.authentication;

import com.cwj.auth.exception.ServiceException;
import com.cwj.datasource.mysql.base.entity.SysUser;
import com.cwj.datasource.mysql.base.service.SysRoleMenuService;
import com.cwj.datasource.mysql.base.service.SysUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * UserDetailsServiceImpl  实现UserDetailsService接口，从系统DB获取当前用户信息，并返回UserDetails类。
 *
 * @author ChengWenjia
 * @date 2022/2/11 08:56
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Resource
    SysRoleMenuService sysRoleMenuService;

    @Resource
    private SysUserInfoService sysUserInfoService;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        //logger.info("UserDetailService ---> : " + userEmail);
        UserDetails userDetails = null;

        if (!StringUtils.hasLength(userEmail)) {
            throw new ServiceException("用户名不能为空");
        } else {
            SysUser metaUser = sysUserInfoService.selectUserByUserEmail(userEmail);
            if (metaUser == null) {
                //logger.info("用户名：'{}' 不存在.", userEmail);
                throw new ServiceException("用户名：'" + userEmail + "' 不存在");
            } else {
                userDetails = createLoginUser(metaUser);
            }
        }
        return userDetails;
    }

    public UserDetails createLoginUser(SysUser metaUser) {
        //LoginUserDetails loginUserDetails = new LoginUserDetails(metaUser.getUserId(), metaUser);
        LoginUserDetails loginUserDetails = new LoginUserDetails(metaUser.getId(), metaUser, sysRoleMenuService.getMenu(metaUser));
        logger.warn("new LoginUserDetails is: {}", loginUserDetails.toString());
        //logger.info("登录用户 ：{} ", loginUserDetails.toString());
        return loginUserDetails;
    }
}