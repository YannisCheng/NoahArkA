package com.cwj.datasource.elasticsearch;

import java.io.*;
import java.util.ArrayList;

/**
 * ReadFileFromLocal 从本地读取文件 测试类
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-07-16 17:04
 */
public class ReadFileFromLocal {
    public static void main(String[] args) {
        try {
            forEachDir();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void forEachDir() throws IOException {
        File fileDir = new File("/Users/yannischeng/Documents/Big_Data/ElasticSearchDunp/诗歌总集/");
        if (fileDir.isDirectory()) {
            File[] files = fileDir.listFiles();
            assert files != null;
            for (File value : files) {
                System.out.println("value.getAbsolutePath value is : " + value.getAbsolutePath());
                int subStart = value.getAbsolutePath().lastIndexOf("/") + 1;
                int subEnd = value.getAbsolutePath().lastIndexOf(".");
                String indexName = value.getAbsolutePath().substring(subStart, subEnd);
                System.out.println("this new index name is : " + indexName);
                //readFileContent(new File(value.getAbsolutePath()));
            }
        }


    }

    private static void readFileContent(File file) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        ArrayList<String[]> arrayList = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            String[] split = line.split(",");
            for (int i = 0; i < split.length; i++) {
                System.out.print(split[i] + " - ");
            }

            arrayList.add(line.split(","));
        }
        System.out.println("all data is : " + arrayList.size());
    }
}
