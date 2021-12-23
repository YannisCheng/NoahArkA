package com.cwj.common.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 拦截的目标是请求的地址，比 {@link MethodInterceptor} 先执行。
 *
 * 需要重写3个方法
 * 这3个方法的作用，调用时间，不同的拦截器之间是怎样的调用顺序需要参考:
 * {@link org.springframework.web.servlet.DispatcherServlet#doDispatch(HttpServletRequest, HttpServletResponse)}
 * 该方法封装了springMVC处理请求的整个过程
 * 与 HandlerInterceptor 功能相似的类是 {@link WebRequestInterceptor}
 *
 * 假设有5个拦截器编号分别为12345，
 * 若一切正常则方法的执行顺序是12345的preHandle，54321的postHandle，54321的afterCompletion。
 * 若编号3的拦截器的preHandle方法返回false或者抛出了异常，接下来会执行的是21的afterCompletion方法。
 * 这里要注意的地方是，我们在写一个拦截器的时候要谨慎的处理preHandle中的异常，因为这里一旦有异常抛出就不会再受到这个拦截器的控制。
 * 12345的preHandle的方法执行过之后，若handler出现了异常或者某个拦截器的postHandle方法出现了异常，
 * 则接下来都会执行54321的afterCompletion方法，因为只要12345的preHandle方法执行完，当前拦截器的拦截器就会记录成编号5的拦截器，而afterCompletion总是从当前的拦截器逆向的向前执行。
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-23 14:35
 */
@Component
public class BaseHandlerInterceptor implements HandlerInterceptor {

    /**
     * 如果返回 true，则意味着请求将继续到达 Controller 被处理。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("1 preHandle request : " + request.getRequestURI());
        System.out.println("1 preHandle response : " + response.toString());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        System.out.println("1 postHandle request : " + request.getRequestURI());
        System.out.println("1 postHandle response : " + response.toString());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        System.out.println("1 afterCompletion request : " + request.getRequestURI());
        System.out.println("1 afterCompletion response : " + response.toString());
    }
}
