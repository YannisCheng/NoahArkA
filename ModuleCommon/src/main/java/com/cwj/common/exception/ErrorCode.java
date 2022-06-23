package com.cwj.common.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * com.cwj.common.result
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-12-31 14:05
 */
@AllArgsConstructor
public enum ErrorCode {

    RESOURCE_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "未找到该资源"),
    REQUEST_VALIDATION_FAILED(1002, HttpStatus.BAD_REQUEST, "请求数据格式验证失败");

    private final int code;
    private final HttpStatus status;
    private final String message;

    public int getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
