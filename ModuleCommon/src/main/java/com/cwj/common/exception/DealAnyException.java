package com.cwj.common.exception;

import org.springframework.http.HttpStatus;

/**
 * DealAnyException  业务异常
 *
 * @author ChengWenjia
 * @since 2022/2/9 14:36
 */
public final class DealAnyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    /**
     * 错误明细，内部调试错误
     */
    private String detailMessage;

    /**
     * 空构造方法，避免反序列化问题
     */
    public DealAnyException() {
    }

    public DealAnyException(String message) {
        this.code = 1;
        this.message = message;
    }

    public DealAnyException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public DealAnyException(String message, HttpStatus unauthorized) {
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public DealAnyException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public DealAnyException setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getCode() {
        return code;
    }
}