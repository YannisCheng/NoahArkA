package com.cwj.common.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ResultBase  返回值包装类
 *
 * @author  ChengWenjia
 * @since    2022/6/14 13:53
*/
@Data
@ApiModel(description = "请求结果返回对象")
public class ResultBase<T> {

    /**
     * 状态值
     */
    @ApiModelProperty(notes = "0成功 其他失败")
    private Integer code;

    /**
     * 提示信息
     */
    @ApiModelProperty(notes = "操作的提示文本")
    private String msg;

    /**
     * 具体内容
     */
    @ApiModelProperty(notes = "操作返回的具体内容")
    private T data;

    @JsonIgnore
    public boolean isSuccess() {
        return ResultEnum.SUCCESS.getCode().equals(code);
    }

    @JsonIgnore
    public boolean isFail() {
        return !ResultEnum.SUCCESS.getCode().equals(code);
    }
}
