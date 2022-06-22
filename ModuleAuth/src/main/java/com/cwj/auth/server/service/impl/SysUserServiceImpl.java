package com.cwj.auth.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cwj.auth.authentication.LoginUserDetails;
import com.cwj.auth.authentication.TokenService;
import com.cwj.auth.server.service.SysUserService;
import com.cwj.auth.server.vo.login.LoginBody;
import com.cwj.auth.server.vo.login.RegisterBody;
import com.cwj.auth.utils.SecurityUtil;
import com.cwj.common.base.result.ResultBase;
import com.cwj.common.base.result.ResultUtils;
import com.cwj.common.utils.ServletUtil;
import com.cwj.datasource.mysql.base.entity.SysUser;
import com.cwj.datasource.mysql.base.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import static com.cwj.common.Constants.Constants.TAG_MAIL;
import static com.cwj.common.Constants.Constants.TAG_PHONE;


/**
 * SysUserInfoServiceImpl
 *
 * @author ChengWenjia
 * @date 2022-02-09 15:23
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private TokenService tokenService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Autowired
    private SysUserRepository sysUserRepository;

    private String userIMPwd = "";

    /**
     * 用户登录
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @Override
    public ResultBase login(LoginBody loginBody) {


        ResultBase result = null;
        LoginUserDetails userDetails = null;
        String exceptionStr = "";
        try {
            if (loginBody.getLoginTag() == TAG_MAIL) {
                // 邮箱登录
                userDetails = doAuthentication(loginBody.getUserEmail(), loginBody.getPassword());
            } else if (loginBody.getLoginTag() == TAG_PHONE) {
                // 手机登录
                System.out.println("手机登录方式");
            }
        } catch (Exception e) {
            System.out.println("login exception is : " + e.toString());
            exceptionStr = "当前用户认证失败。" + e.getMessage();
            if (e instanceof BadCredentialsException) {
                System.out.println("AAAAAAAA========密码错误");
                exceptionStr = "当前用户认证失败。密码错误";
            } else {
                System.out.println("AAAAAAAA========" + e.getMessage());
                exceptionStr = "当前用户认证失败。" + e.getMessage();
            }
        }

        if (userDetails == null) {
            result = ResultUtils.errorData(exceptionStr);
        } else {
            // 生成token
            String userToken = tokenService.createUserToken(userDetails);
            // TODO  2022-06-21 17:31:39 其他信息配置
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userInfo", userDetails.getSysUser());
            jsonObject.put("token", userToken);
            result = ResultUtils.success("", jsonObject);
        }

        return result;
    }

    /**
     * 认证操作
     *
     * @param mail     mail
     * @param password password
     * @return details
     */
    private LoginUserDetails doAuthentication(String mail, String password) throws Exception {
        LoginUserDetails loginUserDetails = null;
        Authentication authentication = null;

        // 该方法会去调用  {@link UserDetailsServiceImpl#loadUserByUsername()}
        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mail, password));

        if (authentication != null) {
            // TODO 2022-02-09 15:45:32 日志操作
            //AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtil.message("user.login.success")));
            loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        }

        return loginUserDetails;

    }

    /**
     * 用户退出登录
     *
     * @return 结果
     */
    @Override
    public ResultBase logout() {
        ResultBase resultMap = null;

        LoginUserDetails loginUserDetails = tokenService.getLoginUser(ServletUtil.getRequest());
        if (loginUserDetails != null) {
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUserDetails.getToken());
            // TODO 2022-02-14 17:12:36 记录用户退出日志
            // String userName = loginUserDetails.getUsername();
            //AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
            resultMap = ResultUtils.success("登出成功");
        } else {
            resultMap = ResultUtils.errorData("登出异常");
        }

        return resultMap;
    }

    /**
     * 新增保存用户信息
     *
     * @param registerBody 用户注册信息
     * @return 注册结果
     */
    @Override
    public ResultBase registryUser(RegisterBody registerBody) {

        ResultBase resultMap = null;
        if (registerBody.getUserGender() == null || !StringUtils.hasLength(registerBody.getUserGender())) {
            registerBody.setUserGender("0");
        }
        if (registerBody.getRegisterTag() == TAG_MAIL) {
            // 邮箱注册
            if (sysUserRepository.existsByEmail(registerBody.getUserEmail())) {
                resultMap = ResultUtils.errorData("当前邮箱已注册，请重新编辑。");
            } else {
                SysUser metaUser = setMetaUser(registerBody);
                SysUser insertMetaUser = sysUserRepository.save(metaUser);
                if (insertMetaUser.getId() != 0) {
                    resultMap = ResultUtils.success("注册成功", insertMetaUser);
                } else {
                    resultMap = ResultUtils.errorData("注册失败");
                }

            }
        } else if (registerBody.getRegisterTag() == TAG_PHONE) {
            System.out.println("手机登录");
        }

        return resultMap;
    }

    /**
     * 设置MetaUser信息
     *
     * @param registerBody 用户信息
     * @return 结果
     */
    private SysUser setMetaUser(RegisterBody registerBody) {
        SysUser metaUser = new SysUser();
        metaUser.setPassword(SecurityUtil.encryptPassword(registerBody.getPassword()));
        metaUser.setEmail(registerBody.getUserEmail());
        metaUser.setNickName(registerBody.getUserNickName());
        metaUser.setSex(registerBody.getUserGender());
        metaUser.setUserName(registerBody.getUserName());
        return metaUser;
    }
}
