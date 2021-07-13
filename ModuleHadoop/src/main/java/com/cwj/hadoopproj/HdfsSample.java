package com.cwj.hadoopproj;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsSample {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://had-nn:9000"), configuration, "adminc");

        // 增
        //createDirAndUpload(fileSystem);

        // 将hdfs服务器上的文件下载到本地
        //fileSystem.copyToLocalFile(false,new Path("hdfs://had-nn:9000/adminc/tmp/sanguo.txt"), new Path("/Users/yannischeng/BigData/Tmp_Save/sanguo2.txt"),true);

        // 删除 — 递归删除该文件及其内部的所有文件
        //fileSystem.delete(new Path("hdfs://had-nn:9000/adminc/"),true);

        // 查 — 文件详情
        //getFileDetail(fileSystem);

        // 查 — 文件状态
        //getFileStatus(fileSystem);
        fileSystem.close();
    }

    private static void getFileStatus(FileSystem fileSystem) throws IOException {
        FileStatus[] listStatus = fileSystem.listStatus(new Path("/"));
        for (FileStatus status : listStatus) {
            System.out.println(status.toString());
            if (status.isFile()) {
                System.out.println("is File f:" + status.getPath().getName());
            } else {
                System.out.println("is Directory f:" + status.getPath().getName());
            }
        }
    }

    private static void getFileDetail(FileSystem fileSystem) throws IOException {
        // 获取文件详情
        RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path("/"), true);
        while (listFiles.hasNext()) {
            System.out.println(listFiles.next().toString());
        }
    }


    private static void createDirAndUpload(FileSystem fileSystem) throws IOException {
        Path homeDirectory = fileSystem.getHomeDirectory();
        // 获取根目录
        System.out.println("homeDirectory : " + homeDirectory.toString());
        // 创建adminc目录
        boolean mkdirs = fileSystem.mkdirs(new Path("/adminc/tmp"));
        if (mkdirs) {
            System.out.println("mkdir success");
            // 将本地文件上传至hdfs服务器上
            fileSystem.copyFromLocalFile(new Path("/Users/yannischeng/BigData/Tmp_Save/sanguo.txt"), new Path("/adminc/tmp"));
        }
    }

}
