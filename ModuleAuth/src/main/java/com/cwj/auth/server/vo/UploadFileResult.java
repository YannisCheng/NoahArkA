package com.cwj.auth.server.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * UploadFileResult 文件上传返回值
 *
 * @author ChengWenjia
 * @since 2022-02-23 17:07
 */
@NoArgsConstructor
@Data
public class UploadFileResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("result")
    private String result;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("fileid")
    private String fileid;
}
