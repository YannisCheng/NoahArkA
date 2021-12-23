package com.cwj.common.configuration;

import com.cwj.common.interceptor.BaseHandlerInterceptor;
import com.cwj.common.interceptor.SampleHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * com.cwj.common.configuration
 *
 * 此处注册2个Interceptor，目的是为了测试多个Interceptor间的调用顺序。
 * 其中：baseHandlerInterceptor 为实际可用Interceptor，另一个无实际意义。
 *
 * 测试代码的限制范围为：com.cwj.datasource.elasticsearch.controller.EsSearchCenter 类中的所有接口。
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-23 14:49
 */
@Configuration
public class BaseWebConfig implements WebMvcConfigurer {

    @Autowired
    BaseHandlerInterceptor baseHandlerInterceptor;

    @Autowired
    SampleHandlerInterceptor sampleHandlerInterceptor;

    /**
     * 运行顺序：
     * 1.加入的顺序就是拦截器执行的顺序，
     * 2.按顺序执行所有拦截器的preHandle
     * 3.所有的preHandle 执行完再执行全部postHandle 最后是postHandle
     *
     *
     *
     * 1 preHandle request : /NoahArk_Dev/doc.html
     * 1 preHandle response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@4b1eb1ce
     * 2 preHandle request : /NoahArk_Dev/doc.html
     * 2 preHandle response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@4b1eb1ce
     * 2 postHandle request : /NoahArk_Dev/doc.html
     * 2 postHandle response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@4b1eb1ce
     * 1 postHandle request : /NoahArk_Dev/doc.html
     * 1 postHandle response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@4b1eb1ce
     * 2 afterCompletion request : /NoahArk_Dev/doc.html
     * 2 afterCompletion response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@4b1eb1ce
     * 1 afterCompletion request : /NoahArk_Dev/doc.html
     * 1 afterCompletion response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@4b1eb1ce
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseHandlerInterceptor).addPathPatterns("/esSearchCenter/*");
        registry.addInterceptor(sampleHandlerInterceptor).addPathPatterns("/esSearchCenter/*");
    }
}
