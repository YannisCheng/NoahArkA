package com.cwj.auth.authentication;

import com.alibaba.fastjson.JSON;
import com.cwj.common.base.result.ResultUtils;
import com.cwj.common.utils.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;


/**
 * AuthenticationEntryPointImpl  用户登录认证失败处理类
 *
 * @author ChengWenjia
 * @since 2022/2/9 14:51
 */
@Component
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        String msg = "请求访问：" + request.getRequestURI() + "，认证失败，无法访问系统资源。具体异常：" + e.getMessage();
        response.setStatus(401);
        ServletUtil.renderString(response, JSON.toJSONString(ResultUtils.errorData(msg)));

        log.info("------认证失败-----");
        log.info(msg);
    }
}
