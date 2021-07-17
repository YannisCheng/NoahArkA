package com.cwj.datasource.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * ElastiicJavaMain 通过ElasticSearch的Java API向ES中写入数据
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-07-16 16:34
 */
public class ElastiicJavaMain {
    /*
     * 创建日志保存在文件工具类
     */
    public static File logFile = LogFileUtil.createFile();
    public static FileWriter fileWriter;

    static {
        try {
            assert logFile != null;
            fileWriter = new FileWriter(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // 创建客户端
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("had-dn1", 9200, "http")));

        // 创建索引
        forEachDir(client);

        // 关闭客户端
        client.close();
    }

    /**
     * 遍历待处理的文件
     *
     * @param client es客户端
     * @throws IOException 异常
     */
    public static void forEachDir(RestHighLevelClient client) throws IOException {
        File fileDir = new File("/Users/yannischeng/Documents/Big_Data/ElasticSearchDunp/诗歌总集/");
        if (fileDir.isDirectory()) {
            File[] files = fileDir.listFiles();
            assert files != null;
            for (File value : files) {
                System.out.println("value.getAbsolutePath value is : " + value.getAbsolutePath());
                int subStart = value.getAbsolutePath().lastIndexOf("/") + 1;
                int subEnd = value.getAbsolutePath().lastIndexOf(".");
                String indexName = value.getAbsolutePath().substring(subStart, subEnd);
                //System.out.println("this new index name is : " + indexName);

                boolean isIndexCreateSuccess = createIndex(client, indexName);

                if (isIndexCreateSuccess) {
                    //System.out.println("index 创建成功！");
                    fileWriter.write(SimpleDateFormat.getDateTimeInstance().format(new Date()) + "  索引：" + indexName + " 创建成功！---------->>>>>>>---------->>>>>>\n");
                    readFileContent(client, indexName, new File(value.getAbsolutePath()));
                } else {
                    System.out.println("index 创建失败！");
                }
            }
        }
        fileWriter.flush();
        fileWriter.close();
    }

    /**
     * 创建ElasticSearch的index
     *
     * @param client    es客户端
     * @param indexName 索引名称
     * @return 索引创建结果
     * @throws IOException 异常
     */
    private static boolean createIndex(RestHighLevelClient client, String indexName) throws IOException {
        CreateIndexRequest indexRequest = new CreateIndexRequest(indexName);
        // 创建索引属性
        indexRequest.settings(Settings.builder()
                .put("index.number_of_shards", 5)
                .put("index.number_of_replicas", 1)
        );

        XContentBuilder jsonBuilder = setMappingStyleTwo();


        //HashMap<String, Object> mappings = setMappingStyleOne();
        // mapping赋值方式1
        //indexRequest.mapping(jsonBuilder);
        // mapping赋值方式2
        indexRequest.mapping(jsonBuilder);

        // 执行索引创建
        CreateIndexResponse indexResponse = client.indices().create(indexRequest, RequestOptions.DEFAULT);
        boolean acknowledged = indexResponse.isAcknowledged();
        boolean shardsAcknowledged = indexResponse.isShardsAcknowledged();
        // 索引创建结果
        return acknowledged && shardsAcknowledged;
    }

    /**
     * 仅为当前索引index的默认type添加数据
     *
     * @param client    es客户端
     * @param indexName 当前待插入数据的索引名称
     * @param file      当前索引对应的文件名
     * @throws IOException 异常
     */
    private static void readFileContent(RestHighLevelClient client, String indexName, File file) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        IndexRequest request = new IndexRequest(indexName);
        int index = 0;
        while ((line = bufferedReader.readLine()) != null) {

            String[] split = line.split(",");
            if (split.length == 4) {
                index++;
                // 构造数据
                request.id(String.valueOf(index))
                        .source(
                                "title", split[0],
                                "author", split[2],
                                "dynasty", split[1],
                                "content", split[3]
                        );

                // 插入数据：同步方式
                IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
                fileWriter.write(SimpleDateFormat.getDateTimeInstance().format(new Date()) + "  添加数据，id=" + index + "，insert status is：" + indexResponse.toString() + "\n");
            }

        }
        fileWriter.write(SimpleDateFormat.getDateTimeInstance().format(new Date()) + "  索引：" + indexName + " 中的数据共计：" + index + "条 <<<<<<<<--------<<<<<<------<<<<<<<<\n\n");

    }

    /**
     * 创建mapping的方式2
     *
     * @return mapping值
     */
    private static XContentBuilder setMappingStyleTwo() throws IOException {
        XContentBuilder jsonBuilder = XContentFactory.jsonBuilder();
        jsonBuilder.startObject();
        {
            jsonBuilder.startObject("properties");
            {
                // 创建title属性
                jsonBuilder.startObject("title");
                {
                    jsonBuilder.field("type", "text")
                            //插入时分词
                            .field("analyzer", "ik_smart")
                            //搜索时分词
                            .field("search_analyzer", "ik_max_word");
                }
                jsonBuilder.endObject();

                // 创建dynasty属性
                jsonBuilder.startObject("dynasty");
                {
                    jsonBuilder.field("type", "keyword");
                }
                jsonBuilder.endObject();

                // 创建author属性
                jsonBuilder.startObject("author");
                {
                    jsonBuilder.field("type", "text")
                            .field("analyzer", "ik_smart");
                }
                jsonBuilder.endObject();

                // 创建content属性
                jsonBuilder.startObject("content");
                {
                    jsonBuilder.field("type", "text")
                            .field("analyzer", "ik_smart")
                            .field("search_analyzer", "ik_max_word");
                }
                jsonBuilder.endObject();
            }
            jsonBuilder.endObject();
        }
        jsonBuilder.endObject();
        return jsonBuilder;
    }

    /**
     * 创建mapping的方式1
     *
     * @return mapping值
     */
    private static HashMap<String, Object> setMappingStyleOne() {
        // 创建mapping
        // 题目
        HashMap<String, Object> title = new HashMap<>();
        title.put("type", "text");
        // 朝代
        HashMap<String, Object> dynasty = new HashMap<>();
        dynasty.put("type", "keyword");
        // 作者
        HashMap<String, Object> author = new HashMap<>();
        author.put("type", "keyword");
        // 内容
        HashMap<String, Object> content = new HashMap<>();
        content.put("type", "text");

        // 设置属性
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("title", title);
        properties.put("dynasty", dynasty);
        properties.put("author", author);
        properties.put("content", content);

        // 设置mapping参数值
        HashMap<String, Object> mappings = new HashMap<>();
        mappings.put("properties", properties);
        return mappings;
    }
}
