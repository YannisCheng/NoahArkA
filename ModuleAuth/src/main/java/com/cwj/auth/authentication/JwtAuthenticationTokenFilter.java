package com.cwj.auth.authentication;

import com.cwj.auth.utils.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JwtAuthenticationTokenFilter  token过滤器，验证token有效性
 *
 * @author ChengWenjia
 * @since 2022/2/9 14:13
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger("Jwt OncePerRequestFilter ---->");

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        LoginUserDetails loginUserDetails = tokenService.getLoginUser(request);
        //logger.warn("doFilterInternal loginUserDetails: {}", loginUserDetails);
        //logger.warn("doFilterInternal Authentication: {}", SecurityUtil.getAuthentication());
        if (loginUserDetails != null && SecurityUtil.getAuthentication() == null) {
            // 登录未过期
            tokenService.verifyToken(loginUserDetails);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUserDetails, null, loginUserDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //logger.warn("doFilterInternal after verifyToken authenticationToken : {}", authenticationToken.toString());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }

}
