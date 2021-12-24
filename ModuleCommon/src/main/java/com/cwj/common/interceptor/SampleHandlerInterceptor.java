package com.cwj.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * com.cwj.common.interceptor
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-23 14:52
 */
@Component
public class SampleHandlerInterceptor implements HandlerInterceptor {

    public static final String INTERCEPTOR_TAG = "拦截器 2# --> ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(INTERCEPTOR_TAG + "2 preHandle request : " + request.getRequestURI());
        System.out.println(INTERCEPTOR_TAG + "2 preHandle response : " + response.toString());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(INTERCEPTOR_TAG + "2 postHandle request : " + request.getRequestURI());
        System.out.println(INTERCEPTOR_TAG + "2 postHandle response : " + response.toString());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println(INTERCEPTOR_TAG + "2 afterCompletion request : " + request.getRequestURI());
        System.out.println(INTERCEPTOR_TAG + "2 afterCompletion response : " + response.toString());
    }
}
