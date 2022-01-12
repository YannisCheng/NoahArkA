package com.cwj.datasource.elasticsearch.controller;

import com.cwj.datasource.elasticsearch.entity.book.BookOfLaoZi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

/**
 * com.cwj.datasource.elasticsearch.controller 搜索查询管理Center
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-22 10:39
 */
@RestController
@RequestMapping(value = "/esSearchCenter")
@Api(tags = "Search管理Center")
@Validated
public class EsSearchCenter {

    @Autowired
    RestHighLevelClient client;

    @Autowired
    ElasticsearchOperations operations;

    private final RequestOptions options = RequestOptions.DEFAULT;

    @GetMapping(value = "/searchAllByClient")
    @ApiOperation(value = "搜索：搜索查询-MatchAll", notes = "搜索查询-MatchAll：search适用于不知道任何doc元数据的情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "索引名称", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query")
    })
    public SearchResponse searchAllByClient(@Valid @RequestParam("indexName") String indexName) throws IOException {
        // 限制搜索范围为：indexName 索引
        SearchRequest searchRequest = new SearchRequest(indexName);
        // 设置搜索行为限制
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.matchAllQuery());

        searchRequest.source(sourceBuilder);
        return client.search(searchRequest, options);
    }

    @GetMapping(value = "/searchParamByClient")
    @ApiOperation(value = "搜索：搜索查询-wildcard模糊查询", notes = "搜索查询-wildcard模糊查询：search适用于不知道任何doc元数据的情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "索引名称", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query"),
            @ApiImplicitParam(name = "params", value = "查询条件", required = true, dataType = "String", defaultValue = "道可道", paramType = "query")
    })
    public SearchResponse searchParamByClient(@Valid @RequestParam("indexName") String indexName, @Valid @RequestParam("param") String param) throws IOException {
        // 限制搜索范围为：indexName 索引
        SearchRequest searchRequest = new SearchRequest(indexName);
        // 设置搜索行为限制
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.wildcardQuery("content", param));

        searchRequest.source(sourceBuilder);
        return client.search(searchRequest, options);
    }

    @GetMapping(value = "/queryByPageClient")
    @ApiOperation(value = "通过使用RestHighLevelClient进行分页查询", notes = "使用RestHighLevelClient进行分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "索引名称", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query"),
            @ApiImplicitParam(name = "from", value = "起始页", required = true, dataType = "int", defaultValue = "0", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "单页数量", required = true, dataType = "int", defaultValue = "100", paramType = "query")
    })
    public SearchResponse queryByPageClient(@Valid @RequestParam("indexName") String indexName, @Valid @RequestParam("from") int from, @Valid @RequestParam("size") int size) throws IOException {
        from = from <= -1 ? 0 : from;
        size = Math.min(size, 1000);
        size = size <= 0 ? 15 : size;
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(from);
        builder.size(size);

        SearchRequest request = new SearchRequest();
        request.indices(indexName);
        request.source(builder);
        System.out.println("请求String：" + request.source().toString());

        return client.search(request, options);
    }

    @GetMapping(value = "/searchCountByClient")
    @ApiOperation(value = "搜索：搜索查询-总数量", notes = "搜索查询-index中doc数量")
    @ApiImplicitParam(name = "indexName", value = "索引名称", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query")
    public CountResponse searchCountByClient(@Valid @RequestParam("indexName") String indexName) throws IOException {
        CountRequest countRequest = new CountRequest(indexName);
        return client.count(countRequest, options);
    }

    @GetMapping(value = "/searchBoolByOptions")
    @ApiOperation(value = "搜索：bool多条件查询-ElasticSearchOptions", notes = "bool多条件查询：ElasticSearchOptions & NativeSearchQuery")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "索引名称", required = true, dataType = "String", defaultValue = "book_lao_zi", paramType = "query"),
            @ApiImplicitParam(name = "paramMust", value = "查询条件Must", required = true, dataType = "String", defaultValue = "致虚极", paramType = "query"),
            @ApiImplicitParam(name = "paramMustNot", value = "查询条件MustNot", required = true, dataType = "String", defaultValue = "道", paramType = "query"),
    })
    public SearchHits<BookOfLaoZi> searchParamByOptions(@Valid @RequestParam("indexName") String indexName, @Valid @RequestParam("paramMust") String paramMust, @Valid @RequestParam("paramMustNot") String paramMustNot) {

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchQuery("content", paramMust));
        boolQuery.mustNot(QueryBuilders.matchQuery("content", paramMustNot));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.withQuery(boolQuery).build();

        return operations.search(nativeSearchQuery, BookOfLaoZi.class, IndexCoordinates.of(indexName));
    }

    @GetMapping(value = "/getById")
    @ApiOperation(value = "通过_id查询", notes = "通过_id查询，id为String类型")
    @ApiImplicitParam(name = "id", value = "index中的id值", required = true, dataType = "String", defaultValue = "qrdx2323-23u9h223XNf", paramType = "query")
    public BookOfLaoZi getItemById(@Valid @RequestParam("id") String id) {
        return operations.get(id, BookOfLaoZi.class);
    }
}
