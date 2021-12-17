package com.cwj.datasource.elasticsearch.controller;

import com.cwj.datasource.configuration.ElasticSearchClientConfig;
import com.cwj.datasource.elasticsearch.entity.book.BookOfLaoZi;
import com.cwj.datasource.elasticsearch.service.SampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.cwj.datasource.elasticsearch.controller
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-16 22:33
 */
@RestController()
@RequestMapping(value = "/sample")
@Api(tags = "ElasticsearchOperations 操作《老子》index")
public class ElasticOpeController {

    /**
     * 该配置项已经在 @link ElasticSearchClientConfig 进行配置。 {@link ElasticSearchClientConfig}
     *  @see ElasticSearchClientConfig
     */
    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    SampleService sampleService;

    @GetMapping(value = "/getById")
    @ApiOperation(value = "通过_id查询", notes = "通过_id查询，id为String类型")
    @ApiImplicitParam(name = "id", value = "index中的id值", required = true, dataType = "String", defaultValue = "qrdx23kB-zvu9h2xrXNf", paramType = "query")
    public BookOfLaoZi getItemById(String id) {

        //
        return operations.get(id, BookOfLaoZi.class);
    }

    @GetMapping(value = "/findAll")
    @ApiOperation(value = "通过 ElasticSearch Repository findAll", notes = "find all")
    public Page<BookOfLaoZi> findAll() {
        PageRequest request = PageRequest.of(0, 100);
        return sampleService.findAll(request);
    }

}
