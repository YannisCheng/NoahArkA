package com.cwj.datasource.elasticsearch.controller;

import com.cwj.datasource.elasticsearch.local.ElasticJavaMain;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsRequest;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * com.cwj.datasource.elasticsearch.controller 索引管理Center
 * ES中提供的索引管理类：{@link org.elasticsearch.client.IndicesClient}
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-22 10:33
 */
@RestController
@RequestMapping(value = "/esIndexCenter")
@Api(tags = "Indices管理Center")
public class EsIndexCenter {

    @Autowired
    RestHighLevelClient client;

    private final RequestOptions options = RequestOptions.DEFAULT;

    @GetMapping(value = "/index/check")
    @ApiOperation(value = "index：是否存在", notes = "index管理：index是否存在")
    @ApiImplicitParam(name = "indexName", value = "索引名称", defaultValue = "book_temp", required = true, dataType = "String")
    public boolean checkIndexExist(String indexName) {
        try {
            return client.indices().exists(new GetIndexRequest(indexName), options);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @see ElasticJavaMain#createIndex(RestHighLevelClient, String)
     * 创建 索引，具体实例参考：{@link ElasticJavaMain#createIndex(RestHighLevelClient client, String index)}
     */
    @GetMapping(value = "/index/create")
    @ApiOperation(value = "index：增/创建", notes = "index管理：新增/创建index")
    @ApiImplicitParam(name = "indexName", value = "索引名称", defaultValue = "book_temp", required = true, dataType = "String")
    public boolean createIndex(String indexName, Map<String, Object> mapping) {

        if (!checkIndexExist(indexName)) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
            if (mapping != null && mapping.size() != 0) {
                HashMap<String, Object> source = new HashMap<>();
                source.put("properties", mapping);
                createIndexRequest.mapping(source);
                try {
                    CreateIndexResponse response = client.indices().create(createIndexRequest, options);
                    return response.isAcknowledged();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @GetMapping(value = "/index/delete")
    @ApiOperation(value = "index：删除", notes = "index管理：删除index")
    @ApiImplicitParam(name = "indexName", value = "索引名称", defaultValue = "book_temp", required = true, dataType = "String")
    public boolean deleteIndex(String indexName) {
        if (checkIndexExist(indexName)) {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
            try {
                AcknowledgedResponse response = client.indices().delete(deleteIndexRequest, options);
                return response.isAcknowledged();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    //@GetMapping(value = "/index/Info")
    //@ApiOperation(value = "index：查", notes = "index管理：获取index信息")
    //@ApiImplicitParam(name = "indexName", value = "索引名称", defaultValue = "book_temp", required = true, dataType = "String")
    //public GetIndexResponse getIndexInfo(String indexName) {
    //    if (checkIndexExist(indexName)) {
    //        GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
    //        try {
    //            return client.indices().get(getIndexRequest, options);
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //    }
    //
    //    return null;
    //}

    //@GetMapping(value = "/indexMapping/update")
    //@ApiOperation(value = "indexMapping：改/更新", notes = "index管理Mapping：更新indexMapping")
    //@ApiImplicitParam(name = "indexName", value = "索引名称", defaultValue = "book_temp", required = true, dataType = "String")
    //public GetMappingsResponse updateIndexMapping(String indexName) {
    //
    //
    //    return null;
    //}

    @GetMapping(value = "/indexMapping/Info")
    @ApiOperation(value = "indexMapping：查", notes = "index管理Mapping：获取indexMapping信息")
    @ApiImplicitParam(name = "indexName", value = "索引名称", defaultValue = "book_temp", required = true, dataType = "String")
    public GetMappingsResponse getIndexMappingInfo(String indexName) {

        if (checkIndexExist(indexName)) {
            GetMappingsRequest mappingsRequest = new GetMappingsRequest().indices(indexName);
            try {
                return client.indices().getMapping(mappingsRequest, options);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @GetMapping(value = "/indexSetting/Info")
    @ApiOperation(value = "indexSetting：查", notes = "index管理Setting：获取indexSetting信息")
    @ApiImplicitParam(name = "indexName", value = "索引名称", defaultValue = "book_temp", required = true, dataType = "String")
    public GetSettingsResponse getIndexSettingInfo(String indexName) {

        if (checkIndexExist(indexName)) {
            GetSettingsRequest settingsRequest = new GetSettingsRequest().indices(indexName);
            try {
                return client.indices().getSettings(settingsRequest, options);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @GetMapping(value = "/indexMapping/field")
    @ApiOperation(value = "indexMapping：field信息", notes = "index管理Mapping：获取indexMapping field 信息")
    @ApiImplicitParam(name = "indexName", value = "索引名称", defaultValue = "book_temp", required = true, dataType = "String")
    public GetFieldMappingsResponse getIndexMappingfieldInfo(String indexName) {
        if (checkIndexExist(indexName)) {
            GetFieldMappingsRequest indices = new GetFieldMappingsRequest().indices(indexName);
            try {
                return client.indices().getFieldMapping(indices, options);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
