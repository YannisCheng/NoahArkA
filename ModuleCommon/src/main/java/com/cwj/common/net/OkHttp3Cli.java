package com.cwj.common.net;

import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import static org.springframework.http.MediaType.*;

/**
 * @author ChengWenjia
 * @since 2022-08-04 10:07
 */
@Component
public class OkHttp3Cli {

    @Resource
    private OkHttpClient okHttpClient;

    /**
     * get 请求-同步
     *
     * @param url     url
     * @param params  参数
     * @param headers 请求头
     * @return 响应体
     */
    public String doGet(String url, Map<String, String> params, String[] headers) {
        return doGetRequest(url, params, headers);
    }

    /**
     * get 同步请求封装
     *
     * @param url     请求url地址
     * @param params  请求参数 map
     * @param headers 请求头字段 {k1, v1 k2, v2, ...}
     * @return string
     */
    public String doGetRequest(String url, Map<String, String> params, String[] headers) {

        StringBuilder sb = new StringBuilder(url);
        if (params != null && params.keySet().size() > 0) {
            boolean firstFlag = true;
            for (String key : params.keySet()) {
                if (firstFlag) {
                    sb.append("?").append(key).append("=").append(params.get(key));
                    firstFlag = false;
                } else {
                    sb.append("&").append(key).append("=").append(params.get(key));
                }
            }
        }

        Request.Builder builder = request(headers);

        Request request = builder.url(sb.toString()).build();
        //log.info("do get request and url[{}]", sb.toString());
        return execute(request);
    }

    /**
     * post 请求
     *
     * @param url    请求url地址
     * @param params 请求参数 map
     * @return string
     */
    public String doPost(String url, Map<String, String> params, String[] headers) {
        FormBody.Builder fromBody = new FormBody.Builder();

        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                fromBody.add(key, params.get(key));
            }
        }
        Request.Builder builder = request(headers);
        Request request = builder.url(url).post(fromBody.build()).build();
        //log.info("do post request and url[{}]", url);

        return execute(request);
    }

    /**
     * post 请求, 请求数据为 json 的字符串
     *
     * @param url  请求url地址
     * @param json 请求数据, json 字符串
     * @return string
     */
    public String doPostJson(String url, String json, String[] headers) {
        //log.info("do post request and url[{}]", url);
        return doPostRequest(url, MediaType.parse(APPLICATION_JSON_VALUE), json, headers);
    }

    /**
     * post 请求, 请求数据为 xml 的字符串
     *
     * @param url 请求url地址
     * @param xml 请求数据, xml 字符串
     * @return string
     */
    public String doPostXml(String url, String xml, String[] headers) {
        // log.info("do post request and url[{}]", url);
        return doPostRequest(url, MediaType.parse(APPLICATION_ATOM_XML_VALUE), xml, headers);
    }


    private String doPostRequest(String url, MediaType contentType, String data, String[] headers) {
        RequestBody requestBody = RequestBody.create(data, contentType);
        Request.Builder builder = request(headers);
        Request request = builder.url(url).post(requestBody).build();
        return execute(request);
    }

    /**
     * 添加 headers 参数
     *
     * @param headers headers
     * @return 构造器
     */
    private Request.Builder request(String[] headers) {
        Request.Builder builder = new Request.Builder();
        if (headers != null && headers.length > 0) {
            if (headers.length % 2 == 0) {
                for (int i = 0; i < headers.length; i = i + 2) {
                    builder.addHeader(headers[i], headers[i + 1]);
                }
            } else {
                //log.warn("headers's length[{}] is error.", headers.length);
            }
        }

        return builder;
    }

    /**
     * 执行同步请求
     *
     * @param request 请求体
     * @return response 响应体
     */
    private String execute(Request request) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            //log.error(ExceptionUtils.getStackTrace(e));
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }

    /**
     * 文件上传
     *
     * @param url        url
     * @param createUser 固定值
     * @param parentId   固定值
     * @param file       文件
     * @return 返回值
     */
    public String uploadFile(String url, String createUser, String parentId, File file) {

        RequestBody fileBody = RequestBody.create(file, MediaType.parse(APPLICATION_OCTET_STREAM_VALUE));
        String fileName = getValueEncoded(file.getName());

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(Headers.of("Content-Disposition", "form-data; name=\"file\";filename=\"" + fileName + "\""), fileBody)
                .addFormDataPart("createUser", createUser)
                .addFormDataPart("parentId", parentId)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return execute(request);
    }

    private String getValueEncoded(String value) {
        if (value == null) return "null";
        String newValue = value.replace("\n", "");
        for (int i = 0, length = newValue.length(); i < length; i++) {
            char c = newValue.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                try {
                    return URLEncoder.encode(newValue, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return newValue;
    }

}