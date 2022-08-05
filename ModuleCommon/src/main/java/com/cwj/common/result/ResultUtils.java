package com.cwj.common.result;

/**
 * ResultUtils  Result响应工具类
 *
 * @author  ChengWenjia
 * @since    2022/6/14 13:52
*/
public class ResultUtils {

    public static <T> ResultBase<T> success() {
        return resultData(ResultEnum.SUCCESS, null);
    }

    public static <T> ResultBase<T> success(T t) {
        return resultData(ResultEnum.SUCCESS, t);
    }

    public static <T> ResultBase<T> success(String msg) {
        return resultData(ResultEnum.SUCCESS.getCode(), msg, null);
    }

    public static <T> ResultBase<T> success(String msg, T t) {
        return resultData(ResultEnum.SUCCESS.getCode(), msg, t);
    }

    public static <T> ResultBase<T> resultData(ResultEnum resultEnum, T t) {
        return resultData(resultEnum.getCode(), resultEnum.getMsg(), t);
    }

    public static <T> ResultBase<T> errorDefault() {
        return errorData(ResultEnum.ERROR);
    }

    public static <T> ResultBase<T> errorData(ResultEnum resultEnum) {
        return resultData(resultEnum.getCode(), resultEnum.getMsg(), null);
    }

    public static <T> ResultBase<T> errorData(Integer code, String msg) {
        return resultData(code, msg, null);
    }

    public static <T> ResultBase<T> errorData(String msg) {
        return resultData(ResultEnum.ERROR.getCode(), msg, null);
    }

    public static <T> ResultBase<T> errorData() {
        return resultData(ResultEnum.ERROR.getCode(),  "",null);
    }

    private static <T> ResultBase<T> resultData(Integer code, String msg, T t) {

        ResultBase<T> resultBase = new ResultBase<>();
        resultBase.setCode(code);
        resultBase.setMsg(msg);
        resultBase.setData(t);
        return resultBase;
    }
}
