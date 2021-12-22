package com.cwj.datasource.elasticsearch.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * com.cwj.datasource.elasticsearch.controller 数据(Document)管理Center
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-22 10:36
 */
@RestController
@RequestMapping(value = "/esDocCenter")
@Api(tags = "数据(Document)管理Center")
public class EsDocCenter {

    private final RequestOptions options = RequestOptions.DEFAULT;

    @Autowired
    RestHighLevelClient client;

    @GetMapping(value = "/insertOneToIndexClient")
    @ApiOperation(value = "增-单", notes = "通过JSON String方式向索引中插入一条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "索引名称", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query"),
            @ApiImplicitParam(name = "jsonStr", value = "JSON格式的新增数据", required = true, dataType = "String", defaultValue = "{\"book_item\":\"\",\"content\":\" \\r\\n　　测试数据-2 炊者不立，自视者不章，自见者不明，自伐者无功，自矜者不长。其在道，曰[米余]食赘行。物或恶之，故有欲者弗居。曲则金，枉则定，洼则盈，敝则新，少则得，多则惑。是以声人执一，以为天下牧。不自视故章，不自见故明，不自伐故有功，不自矜故能长。夫唯不争，故莫能与之争。古之所胃曲金者，几语才？诚金归之。\"}", paramType = "query"),
            @ApiImplicitParam(name = "insertId", value = "新增数据的id值", required = true, dataType = "String", defaultValue = "qrdx2323-23u9h223XNf", paramType = "query")
    })
    public RestStatus insertOneToIndexClient(String indexName, String jsonStr, String insertId) throws IOException {
        IndexRequest indexRequest = new IndexRequest(indexName).id(insertId).source(jsonStr, XContentType.JSON);
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        return response.status();
    }

    @GetMapping(value = "/deleteByClient")
    @ApiOperation(value = "删-单", notes = "删除数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "索引名称", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query"),
            @ApiImplicitParam(name = "delId", value = "元数据在doc中的id值", required = true, dataType = "String", defaultValue = "qrdx2323-23u9h223XNf", paramType = "query")
    })
    public RestStatus deleteByClient(String indexName, String delId) throws IOException {
        DeleteRequest request = new DeleteRequest(indexName, delId);
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        return response.status();
    }

    @GetMapping(value = "/updateOneToDocClient")
    @ApiOperation(value = "改-单", notes = "通过JSON方式向索引中更新一条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "索引名称", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query"),
            @ApiImplicitParam(name = "updateId", value = "元数据在doc中的id值", required = true, dataType = "String", defaultValue = "qrdx2323-23u9h223XNf", paramType = "query"),
            @ApiImplicitParam(name = "jsonStr", value = "JSON格式的新增数据", required = true, dataType = "String", defaultValue = "{\"book_item\":\"\",\"content\":\" \\r\\n　　测试数据-2 炊者不立，自视者不章，自见者不明，自伐者无功，自矜者不长。其在道，曰[米余]食赘行。物或恶之，故有欲者弗居。曲则金，枉则定，洼则盈，敝则新，少则得，多则惑。是以声人执一，以为天下牧。不自视故章，不自见故明，不自伐故有功，不自矜故能长。夫唯不争，故莫能与之争。古之所胃曲金者，几语才？诚金归之。\"}", paramType = "query")
    })
    public RestStatus updateOneToDocClient(String indexName, String updateId, String jsonStr) throws IOException {
        UpdateRequest request = new UpdateRequest(indexName, updateId).doc(jsonStr, XContentType.JSON);
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        return response.status();
    }

    @GetMapping(value = "/queryByGetClient")
    @ApiOperation(value = "查-单", notes = "适用于查询单条数据的详细信息，由查询条件可知需要doc的id值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "索引名称", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query"),
            @ApiImplicitParam(name = "includes", value = "doc查询结果中被包含的字段", required = true, dataType = "String", defaultValue = "content", paramType = "query"),
            @ApiImplicitParam(name = "queryId", value = "被查询doc的id值", required = true, dataType = "String", defaultValue = "qrdx2323-23u9h223XNf", paramType = "query")
    })
    public String queryByGetClient(String indexName, String queryId, String... includes) throws IOException {
        GetRequest getRequest = new GetRequest(indexName, queryId);
        // 1条doc结果中需要返回的数据列（字段值）
        //String[] includes = new String[]{};
        //String[] excludes = new String[]{""};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext sourceContext = new FetchSourceContext(true, includes, excludes);
        GetRequest request = getRequest.fetchSourceContext(sourceContext);
        GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
        return getResponse.getSourceAsString();
    }

    @GetMapping(value = "/bulkOptByClient")
    @ApiOperation(value = "批处理", notes = "批量处理各种操作-插入2条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "索引名称", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query"),
            @ApiImplicitParam(name = "jsonStr1", value = "被插入的数据1", required = true, dataType = "String", defaultValue = "{\"book_item\":\"\",\"content\":\" \\r\\n　　测试数据-2 炊者不立，自视者不章，自见者不明，自伐者无功，自矜者不长。其在道，曰[米余]食赘行。物或恶之，故有欲者弗居。曲则金，枉则定，洼则盈，敝则新，少则得，多则惑。是以声人执一，以为天下牧。不自视故章，不自见故明，不自伐故有功，不自矜故能长。夫唯不争，故莫能与之争。古之所胃曲金者，几语才？诚金归之。\"}", paramType = "query"),
            @ApiImplicitParam(name = "insertId", value = "被插入的数据id", required = true, dataType = "String", defaultValue = "qrdx2323-23u9h223XNt", paramType = "query")
    })
    public RestStatus bulkOptByClient(String indexName, String jsonStr1, String insertId) throws IOException {
        BulkRequest bulkRequest = new BulkRequest()
                .add(new IndexRequest(indexName).id(insertId).source(jsonStr1, XContentType.JSON))
                .add(new DeleteRequest(indexName).id(insertId));
        BulkResponse responses = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        return responses.status();
    }


}
