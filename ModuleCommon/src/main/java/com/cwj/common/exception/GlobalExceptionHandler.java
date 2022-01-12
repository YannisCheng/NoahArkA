package com.cwj.common.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * com.cwj.common.result
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-31 14:18
 */
// 为该类添加@ControllerAdvice注解后，这个类就成为了全局异常处理类，
// 也可以通过 assignableTypes 指定特定的 Controller 类，让异常处理类只处理特定类(特定的Controller)抛出的异常，如：
// @ControllerAdvice(assignableTypes = {ExceptionController.class})
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 一般情况下1个方法值处理特定的1种异常
     * 如果添加value值，则表示该方法仅处理该种异常类型
     * ExceptionHandler(value = BaseException.class)
     * 不添加value值，则表示该方法将会处理所有的异常类型
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAppException(BaseException be, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse(be, request.getRequestURI());
        return new ResponseEntity<>(response,new HttpHeaders(),be.getError().getStatus());
    }
}
