package com.cwj.datasource.elasticsearch.controller;

import com.cwj.datasource.configuration.ElasticSearchClientConfig;
import com.cwj.datasource.elasticsearch.entity.book.BookOfLaoZi;
import com.cwj.datasource.elasticsearch.service.SampleService;
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
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

/**
 * com.cwj.datasource.elasticsearch.controller ElasticSearch ????????????????????????
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-16 22:33
 */
//@RestController
//@RequestMapping(value = "/es_sample")
//@Api(tags = "ES ????????????????????????")
public class ElasticOpeController {

    /**
     * ?????????????????????json string
     */
    public static final String jsonStr = "{\"book_item\":\"\",\"content\":\" \\r\\n??????????????????-2 ??????????????????????????????????????????????????????????????????????????????????????????????????????[??????]????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\"}";

    /**
     * doc??????id???
     */
    public static final String insertId = "qrdx2323-23u9h223XNf";

    /**
     * ?????????RestHighLevelClient {@link ElasticSearchClientConfig} ??????????????????ElasticsearchOperations?????????
     */
    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    SampleService sampleService;

    @Autowired
    RestHighLevelClient client;

    @GetMapping(value = "/getById")
    @ApiOperation(value = "??????_id??????", notes = "??????_id?????????id???String??????")
    @ApiImplicitParam(name = "id", value = "index??????id???", required = true, dataType = "String", defaultValue = "qrdx2323-23u9h223XNf", paramType = "query")
    public BookOfLaoZi getItemById(String id) {
        return operations.get(id, BookOfLaoZi.class);
    }

    @GetMapping(value = "/queryAll")
    @ApiOperation(value = "?????? ElasticSearch Repository findAll", notes = "ElasticSearch Repository findAll")
    public Page<BookOfLaoZi> queryAll() {
        PageRequest request = PageRequest.of(0, 100);
        return sampleService.findAll(request);
    }

    @GetMapping(value = "/queryByPageClient")
    @ApiOperation(value = "????????????RestHighLevelClient??????????????????", notes = "??????RestHighLevelClient??????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "????????????", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query"),
            @ApiImplicitParam(name = "from", value = "?????????", required = true, dataType = "int", defaultValue = "0", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "????????????", required = true, dataType = "int", defaultValue = "100", paramType = "query")
    })
    public SearchResponse queryByPageClient(String indexName, int from, int size) throws IOException {
        from = from <= -1 ? 0 : from;
        size = Math.min(size, 1000);
        size = size <= 0 ? 15 : size;
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(from);
        builder.size(size);

        SearchRequest request = new SearchRequest();
        request.indices(indexName);
        request.source(builder);
        System.out.println("??????String???" + request.source().toString());

        return client.search(request, RequestOptions.DEFAULT);
    }

    @GetMapping(value = "/insertOneToIndexClient")
    @ApiOperation(value = "???-???", notes = "??????JSON String????????????????????????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "????????????", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query"),
            @ApiImplicitParam(name = "jsonStr", value = "JSON?????????????????????", required = true, dataType = "String", defaultValue = "{\"book_item\":\"\",\"content\":\" \\r\\n??????????????????-2 ??????????????????????????????????????????????????????????????????????????????????????????????????????[??????]????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\"}", paramType = "query"),
            @ApiImplicitParam(name = "insertId", value = "???????????????id???", required = true, dataType = "String", defaultValue = "qrdx2323-23u9h223XNf", paramType = "query")
    })
    public RestStatus insertOneToIndexClient(String indexName, String jsonStr, String insertId) throws IOException {
        IndexRequest indexRequest = new IndexRequest(indexName).id(insertId).source(jsonStr, XContentType.JSON);
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        return response.status();
    }

    @GetMapping(value = "/deleteByClient")
    @ApiOperation(value = "???-???", notes = "????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "????????????", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query"),
            @ApiImplicitParam(name = "delId", value = "????????????doc??????id???", required = true, dataType = "String", defaultValue = "qrdx2323-23u9h223XNf", paramType = "query")
    })
    public RestStatus deleteByClient(String indexName, String delId) throws IOException {
        DeleteRequest request = new DeleteRequest(indexName, delId);
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        return response.status();
    }

    @GetMapping(value = "/updateOneToDocClient")
    @ApiOperation(value = "???-???", notes = "??????JSON????????????????????????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "????????????", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query"),
            @ApiImplicitParam(name = "updateId", value = "????????????doc??????id???", required = true, dataType = "String", defaultValue = "qrdx2323-23u9h223XNf", paramType = "query"),
            @ApiImplicitParam(name = "jsonStr", value = "JSON?????????????????????", required = true, dataType = "String", defaultValue = "{\"book_item\":\"\",\"content\":\" \\r\\n??????????????????-2 ??????????????????????????????????????????????????????????????????????????????????????????????????????[??????]????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\"}", paramType = "query")
    })
    public RestStatus updateOneToDocClient(String indexName, String updateId, String jsonStr) throws IOException {
        UpdateRequest request = new UpdateRequest(indexName, updateId).doc(jsonStr, XContentType.JSON);
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        return response.status();
    }

    @GetMapping(value = "/queryByGetClient")
    @ApiOperation(value = "???-???", notes = "????????????????????????????????????????????????????????????????????????doc???id???")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "????????????", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query"),
            @ApiImplicitParam(name = "includes", value = "doc?????????????????????????????????", required = true, dataType = "String", defaultValue = "content", paramType = "query"),
            @ApiImplicitParam(name = "queryId", value = "?????????doc???id???", required = true, dataType = "String", defaultValue = "qrdx2323-23u9h223XNf", paramType = "query")
    })
    public String queryByGetClient(String indexName, String queryId, String... includes) throws IOException {
        GetRequest getRequest = new GetRequest(indexName, queryId);
        // 1???doc????????????????????????????????????????????????
        //String[] includes = new String[]{};
        //String[] excludes = new String[]{""};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext sourceContext = new FetchSourceContext(true, includes, excludes);
        GetRequest request = getRequest.fetchSourceContext(sourceContext);
        GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
        return getResponse.getSourceAsString();
    }

    @GetMapping(value = "/bulkOptByClient")
    @ApiOperation(value = "?????????", notes = "????????????????????????-??????2?????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "????????????", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query"),
            @ApiImplicitParam(name = "jsonStr1", value = "??????????????????1", required = true, dataType = "String", defaultValue = "{\"book_item\":\"\",\"content\":\" \\r\\n??????????????????-2 ??????????????????????????????????????????????????????????????????????????????????????????????????????[??????]????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\"}", paramType = "query"),
            @ApiImplicitParam(name = "insertId", value = "??????????????????id", required = true, dataType = "String", defaultValue = "qrdx2323-23u9h223XNt", paramType = "query")
    })
    public RestStatus bulkOptByClient(String indexName, String jsonStr1, String insertId) throws IOException {
        BulkRequest bulkRequest = new BulkRequest()
                .add(new IndexRequest(indexName).id(insertId).source(jsonStr1, XContentType.JSON))
                .add(new DeleteRequest(indexName).id(insertId));
        BulkResponse responses = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        return responses.status();
    }

    @GetMapping(value = "/searchAllByClient")
    @ApiOperation(value = "?????????????????????-MatchAll", notes = "????????????-MatchAll???search????????????????????????doc??????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "????????????", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query")
    })
    public SearchResponse searchAllByClient(String indexName) throws IOException {
        // ????????????????????????indexName ??????
        SearchRequest searchRequest = new SearchRequest(indexName);
        // ????????????????????????
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.matchAllQuery());

        searchRequest.source(sourceBuilder);
        return client.search(searchRequest, RequestOptions.DEFAULT);
    }

    @GetMapping(value = "/searchParamByClient")
    @ApiOperation(value = "?????????????????????-wildcard????????????", notes = "????????????-wildcard???????????????search????????????????????????doc??????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "????????????", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query"),
            @ApiImplicitParam(name = "params", value = "????????????", required = true, dataType = "String", defaultValue = "?????????", paramType = "query")
    })
    public SearchResponse searchParamByClient(String indexName, String param) throws IOException {
        // ????????????????????????indexName ??????
        SearchRequest searchRequest = new SearchRequest(indexName);
        // ????????????????????????
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.wildcardQuery("content", param));

        searchRequest.source(sourceBuilder);
        return client.search(searchRequest, RequestOptions.DEFAULT);
    }

    @GetMapping(value = "/searchBoolByOptions")
    @ApiOperation(value = "?????????bool???????????????-ElasticSearchOptions", notes = "bool??????????????????ElasticSearchOptions & NativeSearchQuery")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "????????????", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query"),
            @ApiImplicitParam(name = "paramMust", value = "????????????Must", required = true, dataType = "String", defaultValue = "?????????", paramType = "query"),
            @ApiImplicitParam(name = "paramMustNot", value = "????????????MustNot", required = true, dataType = "String", defaultValue = "???", paramType = "query"),
    })
    public SearchHits<BookOfLaoZi> searchParamByOptions(String indexName, String paramMust, String paramMustNot) {

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchQuery("content", paramMust));
        boolQuery.mustNot(QueryBuilders.matchQuery("content", paramMustNot));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.withQuery(boolQuery).build();

        return operations.search(nativeSearchQuery, BookOfLaoZi.class, IndexCoordinates.of(indexName));
    }


}
