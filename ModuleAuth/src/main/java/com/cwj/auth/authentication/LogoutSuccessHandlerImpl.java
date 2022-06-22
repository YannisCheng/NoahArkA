package com.cwj.auth.authentication;

import com.alibaba.fastjson.JSON;
import com.cwj.common.base.result.ResultUtils;
import com.cwj.common.utils.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LogoutSuccessHandlerImpl  自定义退出处理类 返回成功
 *
 * @author ChengWenjia
 * @date 2022/2/9 15:09
 */
//@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        LoginUserDetails loginUserDetails = tokenService.getLoginUser(request);
        if (loginUserDetails != null) {

            // 删除用户缓存记录
            tokenService.delLoginUser(loginUserDetails.getToken());
            // TODO 2022-02-09 15:08:57 记录用户退出日志
            // String userName = loginUserDetails.getUsername();
            //AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        }
        ServletUtil.renderString(response, JSON.toJSONString(ResultUtils.success("退出成功")));
    }
}
