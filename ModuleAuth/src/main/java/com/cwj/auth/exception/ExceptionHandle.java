package com.cwj.auth.exception;

import com.cwj.common.base.result.ResultBase;
import com.cwj.common.base.result.ResultEnum;
import com.cwj.common.base.result.ResultUtils;
import com.cwj.common.utils.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

    /**
     * 权限校验异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResultBase handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        HttpServletResponse response = ServletUtil.getResponse();
        String requestURI = request.getRequestURI();
        String msg = "请求地址'" + requestURI + "'，权限校验失败：" + e.getMessage() + "，请联系管理员授权";
        response.setStatus(403);
        return ResultUtils.errorData(msg);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBase handle(Exception e) {
        log.error("【系统异常】{}", e);
        if (e instanceof ServiceException) {
            // 自定义异常
            ServiceException serviceException = (ServiceException) e;
            return ResultUtils.errorData(serviceException.getCode(), serviceException.getMessage());
        } else if (e instanceof ConstraintViolationException
                || e instanceof MissingServletRequestParameterException) {
            // 参数校验异常
            return ResultUtils.errorData(ResultEnum.VALID_ERROR.getCode(), ResultEnum.VALID_ERROR.getMsg());
        } else if (e instanceof MethodArgumentNotValidException) {
            // 参数校验异常
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            FieldError fieldError = methodArgumentNotValidException.getBindingResult().getFieldError();
            return ResultUtils.errorData(ResultEnum.VALID_ERROR.getCode(), fieldError.getField() + fieldError.getDefaultMessage());
        } else if (e instanceof DuplicateKeyException) {
            // 向数据库插入数据异常
            DuplicateKeyException duplicateKeyException = (DuplicateKeyException) e;
            Throwable cause = duplicateKeyException.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException) {
                // 键重复，违反唯一约束
                SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException = (SQLIntegrityConstraintViolationException) cause;
                return ResultUtils.errorData(ResultEnum.ERROR.getCode(), sqlIntegrityConstraintViolationException.getMessage());
            }
            return ResultUtils.errorData(ResultEnum.ERROR.getCode(), duplicateKeyException.getMessage());
        }
        // 未知错误（没有在这里处理的其他异常）
        return ResultUtils.errorData(ResultEnum.ERROR_UNKONE.getCode(), e.getMessage());
    }
}