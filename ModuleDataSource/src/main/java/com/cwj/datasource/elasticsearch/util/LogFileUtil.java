package com.cwj.datasource.elasticsearch.util;

import java.io.File;
import java.io.IOException;

/**
 * LogFileUtil 日志保存至文件的工具类
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-07-17 15:24
 */
public class LogFileUtil {

    public static File createFile() {
        // 创建日志文件
        File fileDir = new File("/Users/yannischeng/Documents/Big_Data/ElasticSearchDunp/log.txt");
        if (!fileDir.exists()) {
            try {
                boolean newFile = fileDir.createNewFile();
                if (newFile) {
                    System.out.println("日志文件创建成功");
                    return fileDir;
                } else {
                    System.out.println("日志文件创建失败");
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            boolean delete = fileDir.delete();
        }
        return null;
    }
}
