package com.cwj.common.base.result;

/**
 * ResultEnum
 *
 * @author  ChengWenjia
 * @since 2022/6/14 13:53
 */
public enum ResultEnum {
    ERROR_UNKONE(-1, "未知错误"),
    SUCCESS(0, "操作成功"),
    ERROR(1, "操作失败"),
    VALID_ERROR(2, "参数校验失败，请检查输入是否有误");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
