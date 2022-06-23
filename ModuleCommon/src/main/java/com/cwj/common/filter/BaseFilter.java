package com.cwj.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * com.cwj.common.filter
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-12-23 23:50
 */
@WebFilter(filterName = "BaseFilter", urlPatterns = "/esSearchCenter/*")
public class BaseFilter implements Filter {

    public static final String FILTER_TAG = "过滤器 1# --> ";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(FILTER_TAG + "init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(FILTER_TAG + "doFilter");
        HttpServletRequest request1 = (HttpServletRequest) request;
        String requestUri = request1.getRequestURI();
        System.out.println(FILTER_TAG + "请求的接口为：" + requestUri);
        long startTime = System.currentTimeMillis();
        //通过 doFilter 方法实现过滤功能
        chain.doFilter(request, response);
        // 上面的 doFilter 方法执行结束后用户的请求已经返回
        long endTime = System.currentTimeMillis();
        System.out.println(FILTER_TAG + "该用户的请求已经处理完毕，请求花费的时间为：" + (endTime - startTime));
    }

    @Override
    public void destroy() {
        System.out.println(FILTER_TAG + "destroy");
        Filter.super.destroy();
    }
}
