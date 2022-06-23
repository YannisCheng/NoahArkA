package com.cwj.auth.server.service;

import com.cwj.auth.server.vo.login.LoginBody;
import com.cwj.auth.server.vo.login.RegisterBody;
import com.cwj.common.base.result.ResultBase;

/**
 * SysUserInfoService 系统层级操作用户
 *
 * @author ChengWenjia
 * @since 2022-02-09 15:22
 */
public interface SysUserService {


    /**
     * 新增用户信息
     *
     * @param registerBody 用户信息
     * @return 结果
     */
    ResultBase registryUser(RegisterBody registerBody);


    /**
     * 用户登录
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    ResultBase login(LoginBody loginBody);

    /**
     * 退出登录
     *
     * @return 结果
     */
    ResultBase logout();
}
