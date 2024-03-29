package com.cwj.common.net;

import com.alibaba.fastjson.JSON;
import com.cwj.common.configuration.MetaServerConfig;
import com.cwj.common.configuration.ServerConfig;
import com.cwj.common.result.UploadFileResult;
import com.cwj.common.utils.file.FileUploadUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import static com.cwj.common.Constants.Constants.SUCCESS;

/**
 * @author ChengWenjia
 * @since 2022-02-24 18:02
 */
@Service
// 导入配置文件。注意增加编码方式，否则可能中文乱码
// @PropertySource(value = {"classpath:application-common-net.yml"}, encoding = "UTF-8")
public class FileUploadService {

    //@Value("${file.upload.serverUrl}")
    private String serverUrl = "";

    //@Value("${file.upload.createUser}")
    private String createUser = "";

    //@Value("${file.upload.parentId}")
    private String parentId = "";

    //@Value("${file.upload.uploadUrl}")
    private String uploadUrl = "";

    @Resource
    private ServerConfig serverConfig;

    @Resource
    private OkHttp3Cli okHttp3Cli;

    /**
     * 封装云盘文件上传接口
     *
     * @param multipartFile 待上传的源文件
     * @param newFileName   新文件名
     * @return url
     */
    public String doUploadFile(MultipartFile multipartFile, String newFileName) {
        String result;
        File file = null;

        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        if (StringUtils.hasLength(fileName)) {
            // 获取文件后缀
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            try {
                // 不使用一下方式，该方式将生成随机文件名
                //file = File.createTempFile(newFileName, suffix);
                //multipartFile.transferTo(file);
                InputStream ins = multipartFile.getInputStream();
                file = new File(newFileName + suffix);
                inputStreamToFile(ins, file);
                ins.close();

                try {
                    String response = okHttp3Cli.uploadFile(serverUrl + uploadUrl, createUser, parentId, file);
                    UploadFileResult uploadFileResult = JSON.parseObject(response, UploadFileResult.class);
                    if (file.exists()) {
                        file.delete();
                    }
                    if (uploadFileResult.getResult().equals(SUCCESS)) {
                        result = serverUrl + "files/" + uploadFileResult.getFileid() + "/download";
                    } else {
                        result = "文件上传失败";
                    }
                } catch (Exception e) {
                    result = "文件上传接口异常：" + e.getMessage();
                }
            } catch (Exception e) {
                result = "文件创建异常：" + e.getMessage();
            }
        } else {
            result = "文件名不能为空";
        }

        return result;
    }

    /**
     * InputStream 转 File
     *
     * @param ins
     * @param file
     */
    private void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = Files.newOutputStream(file.toPath());
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            System.out.println("转换异常 ： " + e.toString());
        }
    }

    /**
     * 文件上传至自己的服务器
     *
     * @param file 文件
     * @return url
     */
    public String uploadFileOriginal(MultipartFile file) {
        String url = "";
        try {
            // 上传文件路径
            String filePath = MetaServerConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            // 拼接url
            url = serverConfig.getUrl() + fileName;

        } catch (Exception e) {
            url = "上传异常。" + e.getMessage();
        }
        return url;
    }
}
