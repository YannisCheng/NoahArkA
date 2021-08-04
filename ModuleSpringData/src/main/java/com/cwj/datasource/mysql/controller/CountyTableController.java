package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.CountyTable;
import com.cwj.datasource.mysql.service.CountyTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (CountyTable)表控制层
 *
 * @author makejava
 * @since 2021-08-02 20:38:19
 */
@RestController
@RequestMapping("/countyTable")
@Api(tags = "MySQL/区、县county表操作 RestApi", position = 3)
public class CountyTableController {
    /**
     * 服务对象
     */
    @Resource
    private CountyTableService countyTableService;

    /**
     * 通过主键查询单条数据
     *
     * @param countyCode 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    @ApiOperation(value = "byCode查询county表", notes = "通过code值查询")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "countyCode", value = "county表中countyCode值", required = true, dataType = "String",defaultValue = "110108", paramType = "query")
    )
    public CountyTable selectOne(String countyCode) {
        return this.countyTableService.queryById(countyCode);
    }

}