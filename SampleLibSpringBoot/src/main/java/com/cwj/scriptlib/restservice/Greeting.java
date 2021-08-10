package com.cwj.scriptlib.restservice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * com.cwj.scriptlib.restservice
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-07-30 14:43
 */
@ApiModel(value = "Greeting", description = "这是返回值bean的描述信息")
@Data
public class Greeting {
    @ApiModelProperty(value = "调用次数", required = true,example = "1",dataType = "long")
    private final long id;
    @ApiModelProperty(value = "参数内容", name = "contentValue", required = true, example = "word", dataType = "String")
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
